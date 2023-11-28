package org.beizix.admin.usecase.admin.view.application.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.admin.config.adapter.persistence.entity.Privilege;

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
