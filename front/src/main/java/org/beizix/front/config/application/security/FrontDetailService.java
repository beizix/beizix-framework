package org.beizix.front.config.application.security;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.beizix.front.usecase.user.find.ports.FindUserPortIn;
import org.beizix.front.usecase.user.find.ports.application.domain.FindUser;
import org.beizix.front.usecase.user.find.ports.application.domain.FindUserCmd;
import org.beizix.front.usecase.user.find.ports.application.domain.FindUserPrivilege;
import org.beizix.front.usecase.user.find.ports.application.domain.FindUserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FrontDetailService implements UserDetailsService {
  @Value("${app.front.password.validity.period.days}")
  private long passwordValidPeriodDays;

  private final FindUserPortIn findUserPortIn;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    FindUser findUser =
        findUserPortIn
            .operate(new FindUserCmd(username))
            .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));

    boolean accountLocked = Optional.ofNullable(findUser.getAccountLocked()).orElse(false);
    boolean accountDisabled = Optional.ofNullable(findUser.getAccountDisabled()).orElse(false);

    return new FrontUser(
        findUser.getId(),
        findUser.getPassword(),
        !accountDisabled,
        true,
        isCredentialsNonExpired(findUser.getPasswordUpdatedAt()),
        !accountLocked,
        getAuthorities(findUser.getRoles()),
        passwordValidPeriodDays - getDaysPassedFrom(findUser.getPasswordUpdatedAt()));
  }

  /**
   * 패스워드 만료 여부체크. 이 기능이 필요하지 않다면 항상 true 를 반환하도록 변경하면 된다.
   *
   * @param passwordUpdatedAt
   * @return
   */
  private boolean isCredentialsNonExpired(LocalDateTime passwordUpdatedAt) {
    //    return passwordValidPeriodDays == -1
    //        || getDaysPassedFrom(passwordUpdatedAt) <= passwordValidPeriodDays;
    return true;
  }

  /**
   * 패스워드 만료일 정보를 반환. 이 기능이 필요하지 않다면 항상 0L 을 반환하도록 변경하면 된다.
   *
   * @param passwordUpdatedAt
   * @return
   */
  private long getDaysPassedFrom(LocalDateTime passwordUpdatedAt) {
    //    return Duration.between(passwordUpdatedAt, LocalDateTime.now()).toDays();
    return 0L;
  }

  /**
   * Authorities (Role + Privileges) 합성. Role 과 Privileges 는 1:N 매핑되지만, 이 메서드를 통해 동일한 depth 로 배열에 담겨
   * 반환된다. 즉, Privilege 는 또다른 종류의 Role 로 볼 수 있다.
   *
   * @param roles
   * @return authorities
   */
  private Collection<? extends GrantedAuthority> getAuthorities(Collection<FindUserRole> roles) {
    return Stream.concat(getRoles(roles), getPrivileges(roles))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  private Stream<String> getRoles(Collection<FindUserRole> roles) {
    return roles.stream().map(FindUserRole::getId);
  }

  private Stream<String> getPrivileges(Collection<FindUserRole> roles) {
    return roles.stream()
        .flatMap(roleOutput -> roleOutput.getPrivileges().stream())
        .map(FindUserPrivilege::getId);
  }
}
