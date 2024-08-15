package app.module.admin.usecase.admin.list.ports.application.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import app.module.core.config.application.component.AuditOutput;

@Getter
@AllArgsConstructor
public class AdminElement implements AuditOutput {
  private String createdBy;
  private LocalDateTime createdAt;
  private String updatedBy;
  private LocalDateTime updatedAt;
  private String id;
  private String email;
  private Boolean accountDisabled;
  private Boolean accountLocked;
  private List<RoleElement> roles;
}
