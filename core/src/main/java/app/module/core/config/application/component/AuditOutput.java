package app.module.core.config.application.component;

import java.time.LocalDateTime;

public interface AuditOutput {
  String getCreatedBy();
  LocalDateTime getCreatedAt();
  String getUpdatedBy();
  LocalDateTime getUpdatedAt();
}
