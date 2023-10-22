package org.beizix.admin.config.security;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.security.application.domain.admin.model.view.*;
import org.beizix.security.application.port.in.admin.AdminViewPortIn;
import org.beizix.utility.common.PropertyUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {
  private final AdminViewPortIn adminViewPortIn;

  @Value("${org.beizix.password.validity.period.days}")
  private long passwordValidPeriodDays;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AdminViewOutput adminUser =
        adminViewPortIn
            .connect(username)
            .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));

    boolean accountLocked = Optional.ofNullable(adminUser.getAccountLocked()).orElse(false);
    boolean accountDisabled = Optional.ofNullable(adminUser.getAccountDisabled()).orElse(false);

    return new AdminUserDetail(
        adminUser.getId(),
        adminUser.getPassword(),
        !accountDisabled,
        true,
        isCredentialsNonExpired(adminUser.getPasswordUpdatedAt()),
        !accountLocked,
        PropertyUtil.isAdminSingleRole()
            ? getAuthorities(
                adminViewPortIn
                    .connect("beizix-super")
                    .orElseThrow(() -> new RuntimeException("'beizix-super' is not a super user."))
                    .getRoles())
            : getAuthorities(adminUser.getRoles()),
        passwordValidPeriodDays - getDaysPassedFrom(adminUser.getPasswordUpdatedAt()));
  }

  private boolean isCredentialsNonExpired(LocalDateTime passwordUpdatedAt) {
    return passwordValidPeriodDays == -1
        || getDaysPassedFrom(passwordUpdatedAt) <= passwordValidPeriodDays;
  }

  private long getDaysPassedFrom(LocalDateTime passwordUpdatedAt) {
    return Duration.between(passwordUpdatedAt, LocalDateTime.now()).toDays();
  }

  /**
   * Authorities (Role + Privileges) 획득. Role 과 Privileges 는 1:N 매핑되지만, 이 메서드를 통해 동일한 depth 로 배열에 담겨
   * 반환된다. 즉, Privilege 는 또다른 종류의 Role 로 볼 수 있다.
   *
   * @param roles
   * @return authorities
   */
  private Collection<? extends GrantedAuthority> getAuthorities(Collection<RoleOutput> roles) {
    return Stream.concat(getRoles(roles), getPrivileges(roles))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  private Stream<String> getRoles(Collection<RoleOutput> roles) {
    return roles.stream().map(RoleOutput::getId);
  }

  private Stream<String> getPrivileges(Collection<RoleOutput> roles) {
    return roles.stream()
        .flatMap(roleOutput -> roleOutput.getPrivileges().stream())
        .map(PrivilegeOutput::getId);
  }
}
