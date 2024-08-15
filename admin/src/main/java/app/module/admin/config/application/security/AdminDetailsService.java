package app.module.admin.config.application.security;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.transaction.Transactional;

import app.module.admin.usecase.admin.view.ports.AdminViewPortIn;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.admin.view.ports.application.domain.AdminView;
import app.module.admin.usecase.admin.view.ports.application.domain.PrivilegeView;import app.module.admin.usecase.admin.view.ports.application.domain.RoleView;
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

  @Value("${app.admin.password.validity.period.days}")
  private long passwordValidPeriodDays;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AdminView adminUser =
        adminViewPortIn
            .connect(username)
            .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));

    boolean accountLocked = Optional.ofNullable(adminUser.getAccountLocked()).orElse(false);
    boolean accountDisabled = Optional.ofNullable(adminUser.getAccountDisabled()).orElse(false);

    return new AdminUser(
        adminUser.getId(),
        adminUser.getPassword(),
        !accountDisabled,
        true,
        isCredentialsNonExpired(adminUser.getPasswordUpdatedAt()),
        !accountLocked,
        getAuthorities(adminUser.getRoles()),
        passwordValidPeriodDays - getDaysPassedFrom(adminUser.getPasswordUpdatedAt()));
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
  private Collection<? extends GrantedAuthority> getAuthorities(Collection<RoleView> roles) {
    return Stream.concat(getRoles(roles), getPrivileges(roles))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  private Stream<String> getRoles(Collection<RoleView> roles) {
    return roles.stream().map(RoleView::getId);
  }

  private Stream<String> getPrivileges(Collection<RoleView> roles) {
    return roles.stream()
        .flatMap(roleOutput -> roleOutput.getPrivileges().stream())
        .map(PrivilegeView::getId);
  }
}
