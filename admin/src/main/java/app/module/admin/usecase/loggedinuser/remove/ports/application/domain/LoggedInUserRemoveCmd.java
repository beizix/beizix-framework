package app.module.admin.usecase.loggedinuser.remove.ports.application.domain;

import lombok.*;
import app.module.core.config.application.enums.AppType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggedInUserRemoveCmd {
  private AppType appType;
  private String id;
}
