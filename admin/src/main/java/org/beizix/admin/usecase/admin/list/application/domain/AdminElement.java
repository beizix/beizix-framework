package org.beizix.admin.usecase.admin.list.application.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.beizix.core.application.domain.common.model.AuditOutput;

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
