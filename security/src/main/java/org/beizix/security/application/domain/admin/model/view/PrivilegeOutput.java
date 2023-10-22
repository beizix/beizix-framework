package org.beizix.security.application.domain.admin.model.view;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.security.adapter.persistence.privilege.model.Privilege;

/** DTO for {@link Privilege} */
@Getter
@Setter
@AllArgsConstructor
public class PrivilegeOutput implements Serializable {
  private LocalDateTime createdAt;
  private String createdBy;
  private LocalDateTime updatedAt;
  private String updatedBy;
  private String id;
}
