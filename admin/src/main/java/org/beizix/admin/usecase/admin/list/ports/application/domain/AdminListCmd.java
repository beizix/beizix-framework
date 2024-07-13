package org.beizix.admin.usecase.admin.list.ports.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminListCmd {
  private final String searchField;
  private final String searchValue;
  private final String searchRole;
}
