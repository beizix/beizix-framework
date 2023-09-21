package org.beizix.admin.feature.role.web;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.utility.enums.OperationType;

@Data
@NoArgsConstructor
@AllArgsConstructor
class AdminUserRoleDto {

  @NotBlank(message = "{valid.common.required}")
  @Pattern(regexp = "^(ROLE_).+", message = "{valid.adminRole.id.mustStartWith.role}")
  private String role;

  @NotBlank(message = "{valid.common.required}")
  private String description;

  private Integer orderNo;
  private OperationType operationType;

  private List<AdminUserRoleDto> updateList;
}
