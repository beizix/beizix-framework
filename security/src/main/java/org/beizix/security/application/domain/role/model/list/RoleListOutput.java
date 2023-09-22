package org.beizix.security.application.domain.role.model.list;

import java.util.Set;
import lombok.*;
import org.beizix.utility.enums.OperationType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleListOutput {
  private String id;
  private String description;
  private Integer orderNo;
  private Set<WithPrivilegeOutput> withPrivileges;
  private OperationType operationType;
}
