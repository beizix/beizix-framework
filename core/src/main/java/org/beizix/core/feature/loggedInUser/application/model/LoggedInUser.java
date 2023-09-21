package org.beizix.core.feature.loggedInUser.application.model;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoggedInUser {
  private LoggedInUserId loggedInUserId;
  private String clientIP;
  private LocalDateTime lastLoggedInAt;
}
