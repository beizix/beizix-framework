package org.beizix.core.feature.loggedInUser.application.model;

import lombok.*;
import org.beizix.core.config.enums.AppType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggedInUserId {
  private AppType appType;
  private String id;
}
