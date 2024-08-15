package app.module.admin.usecase.admin.view.ports.application.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import app.module.admin.config.adapter.persistence.entity.Privilege;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** DTO for {@link Privilege} */
@Getter
@Setter
@AllArgsConstructor
public class PrivilegeView implements Serializable {
  private LocalDateTime createdAt;
  private String createdBy;
  private LocalDateTime updatedAt;
  private String updatedBy;
  private String id;
}
