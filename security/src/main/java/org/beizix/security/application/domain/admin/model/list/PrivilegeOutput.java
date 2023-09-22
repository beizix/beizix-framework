package org.beizix.security.application.domain.admin.model.list;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.security.adapter.persistence.privilege.model.Privilege;

/** DTO for {@link Privilege} */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegeOutput implements Serializable {
  private String id;
}
