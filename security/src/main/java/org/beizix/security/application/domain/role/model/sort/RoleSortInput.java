package org.beizix.security.application.domain.role.model.sort;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RoleSortInput {
  private String id;
  private Integer orderNo;
}
