package org.beizix.admin.usecase.role.list.adapters.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PrivilegeBindingVO {
  private final String id;
  private final String description;
}
