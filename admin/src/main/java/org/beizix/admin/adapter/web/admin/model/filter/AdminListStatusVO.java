package org.beizix.admin.adapter.web.admin.model.filter;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdminListStatusVO {
  private String searchField;
  private String searchValue;
  private String searchRole;
}
