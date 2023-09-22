package org.beizix.admin.config.security;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.beizix.security.application.domain.admin.model.view.*;
import org.beizix.security.application.port.in.admin.AdminViewPortIn;
import org.beizix.utility.common.PropertyUtil;

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
                    .getWithRoles()
                    .stream()
                    .map(WithRoleOutput::getRole)
                    .collect(Collectors.toList()))
            : getAuthorities(
                adminUser.getWithRoles().stream()
                    .map(WithRoleOutput::getRole)
                    .collect(Collectors.toList())),
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
  private Collection<? extends GrantedAuthority> getAuthorities(
      Collection<RoleOutput> roles) {
    return getGrantedAuthorities(getPrivileges(roles));
  }

  private List<String> getPrivileges(Collection<RoleOutput> roles) {
    List<String> privileges = new ArrayList<>();
    List<PrivilegeOutput> collection = new ArrayList<>();
    for (RoleOutput role : roles) {
      privileges.add(role.getId());
      collection.addAll(
          role.getWithPrivileges().stream()
              .map(WithPrivilegeOutput::getPrivilege)
              .collect(Collectors.toList()));
    }
    for (PrivilegeOutput item : collection) {
      privileges.add(item.getId());
    }
    return privileges;
  }

  private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String privilege : privileges) {
      authorities.add(new SimpleGrantedAuthority(privilege));
    }
    return authorities;
  }
}
