package org.beizix.admin.usecase.loggedinuser.remove.application.domain;

import lombok.*;
import org.beizix.core.configuration.application.enums.AppType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggedInUserRemoveCommand {
  private AppType appType;
  private String id;
}
