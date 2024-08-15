package app.module.core.usecase.operationlog.save.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import app.module.core.config.application.enums.AppType;
import app.module.core.config.application.enums.OperationLogType;

@Getter
@Setter
@AllArgsConstructor
public class OperationLogSaveCommand {

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
