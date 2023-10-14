package org.beizix.security.application.domain.privilege.model.save;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PrivilegeSaveInput {
  private final String id;
  private final String description;
  private Integer orderNo;
}
