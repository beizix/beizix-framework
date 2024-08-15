package app.module.core.usecase.operationlog.list.application.domain;

import java.time.LocalDateTime;

import app.module.core.config.application.component.AuditOutput;
import app.module.core.config.application.enums.AppType;
import app.module.core.config.application.enums.OperationLogType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OperationLogElement implements AuditOutput {
  private String createdBy;
  private LocalDateTime createdAt;

  // 고유키
  private Long id;
  // App 타입
  private AppType appType;
  // 수행 작업 타입
  private OperationLogType operationLogType;
  // 타겟 계정
  private String targetId;

  // 접속 아이피
  private String ip;
  // 수행 내용 설명
  private String taskDesc;

  @Override
  public String getUpdatedBy() {
    return null;
  }

  @Override
  public LocalDateTime getUpdatedAt() {
    return null;
  }
}
