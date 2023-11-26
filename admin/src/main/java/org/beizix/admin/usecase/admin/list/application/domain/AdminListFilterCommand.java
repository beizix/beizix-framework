package org.beizix.admin.usecase.admin.list.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminListFilterCommand {
  private final String searchField;
  private final String searchValue;
  private final String searchRole;
}
