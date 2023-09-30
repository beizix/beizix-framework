package org.beizix.core.application.domain.common.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditBase {
  private String createdBy;
  private String updatedBy;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
