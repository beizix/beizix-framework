package org.beizix.admin.adapter.web.privilege.model.save;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PrivilegeBindingVO {
  private final String id;
  private final String description;
  private Integer orderNo;
}
