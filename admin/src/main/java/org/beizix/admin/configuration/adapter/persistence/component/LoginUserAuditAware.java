package org.beizix.admin.configuration.adapter.persistence.component;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LoginUserAuditAware implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return Optional.of(
        authentication != null && authentication.isAuthenticated()
            ? authentication.getName()
            : "INIT_DATA");
  }
}
