package app.module.core.usecase.user.findUser.ports.application.domain;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class FindUser {
  private String id;
  private String password;
  private String email;
  private LocalDateTime passwordUpdatedAt;
  Boolean accountDisabled;
  Integer loginFailCnt;
  Boolean accountLocked;
  private LocalDateTime createdAt;
  private String createdBy;
  private LocalDateTime updatedAt;
  private String updatedBy;
  private Set<FindUserRole> roles;
}
