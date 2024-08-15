package app.module.admin.usecase.loggedinuser.save.ports.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import app.module.core.config.application.enums.AppType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggedInUserIdSaveCmd {
  private AppType appType;
  private String id;
}

