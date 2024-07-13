package org.beizix.admin.usecase.loggedinuser.save.ports.application.domain;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoggedInUserSaveCmd {
  private LoggedInUserIdSaveCmd loggedInUserId;
  private String clientIP;
  private LocalDateTime lastLoggedInAt;
}
