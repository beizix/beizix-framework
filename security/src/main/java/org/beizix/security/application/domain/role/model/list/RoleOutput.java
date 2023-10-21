package org.beizix.security.application.domain.role.model.list;

import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
import org.beizix.core.application.domain.common.model.AuditOutput;
import org.beizix.utility.enums.OperationType;

@Getter
@Setter
@AllArgsConstructor
public class RoleOutput implements AuditOutput {
  private final String createdBy;
  private final LocalDateTime createdAt;
  private final String updatedBy;
  private final LocalDateTime updatedAt;
  private String id;
  private String description;
  private Integer orderNo;
  private List<PrivilegeOutput> privileges;
}
