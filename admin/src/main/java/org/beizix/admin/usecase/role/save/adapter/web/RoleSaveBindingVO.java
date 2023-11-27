package org.beizix.admin.usecase.role.save.adapter.web;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleSaveBindingVO {
  @NotBlank(message = "{valid.common.required}")
  @Pattern(regexp = "^(ROLE_).+", message = "{valid.adminRole.id.mustStartWith.role}")
  private String id;

  @NotBlank(message = "{valid.common.required}")
  private String description;

  private Integer orderNo;
  private List<String> privilegeIds;
}
