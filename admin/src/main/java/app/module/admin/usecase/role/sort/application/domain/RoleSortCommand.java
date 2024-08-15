package app.module.admin.usecase.role.sort.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RoleSortCommand {
  private String id;
  private Integer orderNo;
}
