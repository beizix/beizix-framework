package app.module.admin.usecase.admin.view.ports.application.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import app.module.admin.config.adapter.persistence.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** DTO for {@link Admin} */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminView implements Serializable {
  private LocalDateTime createdAt;
  private String createdBy;
  private LocalDateTime updatedAt;
  private String updatedBy;
  private String id;
  private String password;
  private String email;
  private LocalDateTime passwordUpdatedAt;
  private Boolean accountDisabled;
  private Integer loginFailCnt;
  private Boolean accountLocked;
  private List<RoleView> roles;
}
