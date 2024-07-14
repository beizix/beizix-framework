package org.beizix.admin.usecase.privilege.list.ports.application.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.config.application.component.AuditOutput;

@Getter
@Setter
@AllArgsConstructor
public class PrivilegeElement implements AuditOutput {
  private final String createdBy;
  private final LocalDateTime createdAt;
  private final String updatedBy;
  private final LocalDateTime updatedAt;
  private final String id;
  private final String description;
  private final Integer orderNo;
}
