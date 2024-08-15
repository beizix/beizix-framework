package org.beizix.front.usecase.user.find.ports.application.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class FindUserRole {
  private String id;
  private String description;
  private Integer orderNo;
  private Set<FindUserPrivilege> privileges;
}
