package org.beizix.core.application.domain.operationlog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.config.enums.OperationLogType;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationLog {
  // 고유키
  private Long id;
  // App 타입
  private AppType appType;
  // 수행 작업 타입
  private OperationLogType operationLogType;
  // 수행 계정
  private String operatorId;
  // 타겟 계정
  private String targetId;
  // 수행 날짜/시간
  private LocalDateTime operatedAt;
  // 접속 아이피
  private String ip;
  // 수행 내용 설명
  private String taskDesc;
}
