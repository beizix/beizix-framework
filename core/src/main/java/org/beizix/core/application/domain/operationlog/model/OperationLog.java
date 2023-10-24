package org.beizix.core.application.domain.operationlog.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.core.application.domain.common.model.AuditOutput;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.config.enums.OperationLogType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationLog implements AuditOutput {
  private String createdBy;
  private LocalDateTime createdAt;
  private String updatedBy;
  private LocalDateTime updatedAt;

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
}
