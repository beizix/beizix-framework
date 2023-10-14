package org.beizix.security.application.domain.privilege.model.view;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.application.domain.common.model.AuditOutput;

@Getter
@Setter
@AllArgsConstructor
public class PrivilegeOutput implements AuditOutput {
  private final String createdBy;
  private final LocalDateTime createdAt;
  private final String updatedBy;
  private final LocalDateTime updatedAt;
  private final String id;
  private final String description;
  private final Integer orderNo;
}
