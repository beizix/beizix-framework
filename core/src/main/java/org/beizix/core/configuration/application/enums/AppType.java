package org.beizix.core.configuration.application.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@RequiredArgsConstructor
public enum AppType {
  FRONT("FRONT APP"),
  ADMIN("ADMIN APP");
  private final String desc;
}
