package org.beizix.admin.usecase.role.view.application.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
import org.beizix.core.application.domain.common.model.AuditOutput;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RoleView implements AuditOutput {
  private final String createdBy;

  private final LocalDateTime createdAt;

  private final String updatedBy;

  private final LocalDateTime updatedAt;

  private String id;
  private String description;
  private Integer orderNo;
  private List<String> privilegeIds;
}
