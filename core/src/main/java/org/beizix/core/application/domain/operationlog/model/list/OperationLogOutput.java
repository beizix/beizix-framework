package org.beizix.core.application.domain.operationlog.model.list;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.application.domain.common.model.AuditOutput;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.config.enums.OperationLogType;

@Getter
@Setter
@AllArgsConstructor
public class OperationLogOutput implements AuditOutput {
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
