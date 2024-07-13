package org.beizix.admin.usecase.loggedinuser.remove.ports.application.domain;

import lombok.*;
import org.beizix.core.config.application.enums.AppType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggedInUserRemoveCmd {
  private AppType appType;
  private String id;
}
