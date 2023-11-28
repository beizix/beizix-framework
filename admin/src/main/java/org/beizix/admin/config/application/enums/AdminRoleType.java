package org.beizix.admin.config.application.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AdminRoleType {
  DEFAULT("ROLE_SUPER", "Super 관리자");
  private final String id;
  private final String desc;
}
