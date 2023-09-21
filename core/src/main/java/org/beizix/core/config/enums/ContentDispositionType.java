package org.beizix.core.config.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContentDispositionType {
  INLINE("인라인 방식"),
  ATTACHMENT("리소스 강제 다운로드 방식");
  private final String desc;
}
