package org.beizix.security.application.domain.admin.model.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminListStatus {
  private final String searchField;
  private final String searchValue;
  private final String searchRole;
}
