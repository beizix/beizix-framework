package org.beizix.admin.usecase.loggedinuser.view.application.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoggedInUserView {
  private LoggedInUserIdView loggedInUserId;
  private String clientIP;
  private LocalDateTime lastLoggedInAt;
}
