package org.beizix.core.application.domain.common.model;

import java.time.LocalDateTime;

public interface AuditOutput {
  String getCreatedBy();
  LocalDateTime getCreatedAt();
  String getUpdatedBy();
  LocalDateTime getUpdatedAt();
}
