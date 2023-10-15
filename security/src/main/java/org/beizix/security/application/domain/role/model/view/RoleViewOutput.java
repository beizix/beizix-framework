package org.beizix.security.application.domain.role.model.view;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.*;
import org.beizix.core.application.domain.common.model.AuditOutput;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RoleViewOutput implements AuditOutput {
  private final String createdBy;

  private final LocalDateTime createdAt;

  private final String updatedBy;

  private final LocalDateTime updatedAt;

  private String id;
  private String description;
  private Integer orderNo;
  private List<String> privilegeIds;
}
