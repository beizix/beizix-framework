package org.beizix.admin.usecase.privilege.save.adapter.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PrivilegeSaveBindingVO {
  private final String id;
  private final String description;
  private Integer orderNo;
}
