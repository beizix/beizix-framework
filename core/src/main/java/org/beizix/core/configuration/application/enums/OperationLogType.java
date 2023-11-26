package org.beizix.core.configuration.application.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OperationLogType {
  LOGIN_SUCCESS("접속 성공"),
  LOGIN_FAIL("접속 실패"),
  ROLE_UPDATE("권한 수정"),
  ACCOUNT_LOCKED("계정 잠김");

  private final String desc;
}
