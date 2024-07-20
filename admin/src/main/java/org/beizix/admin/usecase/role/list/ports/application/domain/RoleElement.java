package org.beizix.admin.usecase.role.list.ports.application.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
import org.beizix.core.config.application.component.AuditOutput;

@Getter
@Setter
@AllArgsConstructor
public class RoleElement implements AuditOutput {
  private final String createdBy;
  private final LocalDateTime createdAt;
  private final String updatedBy;
  private final LocalDateTime updatedAt;
  private String id;
  private String description;
  private Integer orderNo;
  private List<PrivilegeElement> privileges;
}
