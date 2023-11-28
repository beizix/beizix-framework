package org.beizix.core.config.application.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileStorageType {
  LOCAL("로컬 스토리지"),
  S3("AWS S3 스토리지");
  private final String desc;
}
