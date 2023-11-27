package org.beizix.admin.usecase.loggedinuser.view.application.domain;

import lombok.*;
import org.beizix.core.configuration.application.enums.AppType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggedInUserIdView {
  private AppType appType;
  private String id;
}
