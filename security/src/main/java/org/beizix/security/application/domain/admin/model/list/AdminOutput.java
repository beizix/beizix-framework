package org.beizix.security.application.domain.admin.model.list;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.beizix.core.application.domain.common.model.AuditOutput;

@Getter
@AllArgsConstructor
public class AdminOutput implements AuditOutput {
  private String createdBy;
  private LocalDateTime createdAt;
  private String updatedBy;
  private LocalDateTime updatedAt;
  private String id;
  private String email;
  private Boolean accountDisabled;
  private Boolean accountLocked;
  private Set<WithRoleOutput> withRoles;
}
