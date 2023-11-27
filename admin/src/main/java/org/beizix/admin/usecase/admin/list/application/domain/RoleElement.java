package org.beizix.admin.usecase.admin.list.application.domain;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoleElement implements Serializable {
  private String id;
  private String description;
  private Integer orderNo;
  private List<PrivilegeElement> privileges;
}
