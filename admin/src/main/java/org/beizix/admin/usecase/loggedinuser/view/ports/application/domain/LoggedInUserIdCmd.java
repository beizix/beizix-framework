package org.beizix.admin.usecase.loggedinuser.view.ports.application.domain;

import lombok.*;
import org.beizix.core.config.application.enums.AppType;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LoggedInUserIdCmd {
  private AppType appType;
  private String id;
}
