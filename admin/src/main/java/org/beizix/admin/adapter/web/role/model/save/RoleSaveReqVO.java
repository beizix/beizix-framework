package org.beizix.admin.adapter.web.role.model.save;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.utility.enums.OperationType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleSaveReqVO {
  @NotBlank(message = "{valid.common.required}")
  @Pattern(regexp = "^(ROLE_).+", message = "{valid.adminRole.id.mustStartWith.role}")
  private String id;
  @NotBlank(message = "{valid.common.required}")
  private String description;
  private Integer orderNo;
  private OperationType operationType;
}
