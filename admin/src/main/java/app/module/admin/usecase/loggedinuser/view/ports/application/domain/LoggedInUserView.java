package app.module.admin.usecase.loggedinuser.view.ports.application.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoggedInUserView {
  private LoggedInUserIdCmd loggedInUserId;
  private String clientIP;
  private LocalDateTime lastLoggedInAt;
}
