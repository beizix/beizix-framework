package org.beizix.admin.usecase.admin.save.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PrivilegeSaveCommand {
  private final String id;
  private final String description;
  private Integer orderNo;
}
