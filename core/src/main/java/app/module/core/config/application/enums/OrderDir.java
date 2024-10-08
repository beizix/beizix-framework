package app.module.core.config.application.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderDir {
  DESC("내림차순"),
  ASC("오름차순");
  private final String desc;
}
