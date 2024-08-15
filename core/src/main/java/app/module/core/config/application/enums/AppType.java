package app.module.core.config.application.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AppType {
  FRONT("FRONT APP"),
  ADMIN("ADMIN APP");
  private final String desc;
}
