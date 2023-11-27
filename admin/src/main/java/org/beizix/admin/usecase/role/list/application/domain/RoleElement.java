package org.beizix.admin.usecase.role.list.application.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
import org.beizix.core.application.domain.common.model.AuditOutput;
import org.beizix.security.application.domain.role.model.list.PrivilegeOutput;

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
  private List<PrivilegeOutput> privileges;
}
