package org.beizix.admin.usecase.loggedinuser.save.application.domain;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoggedInUserSaveCommand {
  private LoggedInUserIdSaveCommand loggedInUserId;
  private String clientIP;
  private LocalDateTime lastLoggedInAt;
}
