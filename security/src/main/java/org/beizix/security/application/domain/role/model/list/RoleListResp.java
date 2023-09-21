package org.beizix.security.application.domain.role.model.list;

import java.util.Set;
import lombok.*;
import org.beizix.utility.enums.OperationType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleListResp {
  private String id;
  private String description;
  private Integer orderNo;
  private Set<WithPrivilegeResp> withPrivileges;
  private OperationType operationType;
}
