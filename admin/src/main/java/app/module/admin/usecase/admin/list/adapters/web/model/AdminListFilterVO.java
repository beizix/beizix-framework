package app.module.admin.usecase.admin.list.adapters.web.model;

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
