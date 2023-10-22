package org.beizix.security.application.domain.admin.model.list;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoleOutput implements Serializable {
  private String id;
  private String description;
  private Integer orderNo;
  private List<PrivilegeOutput> privileges;
}
