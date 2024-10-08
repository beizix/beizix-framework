package app.module.admin.usecase.admin.view.ports.application.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import app.module.admin.config.adapter.persistence.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** DTO for {@link Role} */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleView implements Serializable {
  private LocalDateTime createdAt;
  private String createdBy;
  private LocalDateTime updatedAt;
  private String updatedBy;
  private String id;
  private String description;
  private Integer orderNo;
  private List<PrivilegeView> privileges;
}
