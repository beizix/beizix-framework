package org.beizix.admin.usecase.loggedinuser.view.application.domain;

import lombok.*;
import org.beizix.core.config.application.enums.AppType;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LoggedInUserIdView {
  private AppType appType;
  private String id;
}
