package app.module.core.usecase.user.findUser.ports.application.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class FindUserPrivilege {
  private String id;
  private String description;
  private Integer orderNo;
}
