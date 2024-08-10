package org.beizix.core.usecase.user.createRole.ports.application.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class CreateRole {
  private String id;
  private String description;
  private Integer orderNo;
  private Set<String> privileges;
}
