package app.module.admin.usecase.admin.list.ports.application.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PrivilegeElement implements Serializable {
  private String id;
}
