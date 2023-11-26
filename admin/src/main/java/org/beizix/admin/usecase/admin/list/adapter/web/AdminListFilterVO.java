package org.beizix.admin.usecase.admin.list.adapter.web;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdminListFilterVO {
  private String searchField;
  private String searchValue;
  private String searchRole;
}
