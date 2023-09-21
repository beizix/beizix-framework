package org.beizix.security.application.domain.role.model.save;

import java.util.Set;
import lombok.*;
import org.beizix.utility.enums.OperationType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleSaveReq {
  private String id;
  private String description;
  private Integer orderNo;
  private Set<WithPrivilegeSaveReq> withPrivileges;
  private OperationType operationType;
}
