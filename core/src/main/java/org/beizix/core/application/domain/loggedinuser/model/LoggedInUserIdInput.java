package org.beizix.core.application.domain.loggedinuser.model;

import lombok.*;
import org.beizix.core.configuration.application.enums.AppType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggedInUserIdInput {
  private AppType appType;
  private String id;
}
