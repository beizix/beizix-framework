package org.beizix.core.application.domain.loggedinuser.model;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoggedInUserInput {
  private LoggedInUserIdInput loggedInUserId;
  private String clientIP;
  private LocalDateTime lastLoggedInAt;
}
