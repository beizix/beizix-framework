package org.beizix.admin.adapter.web.role.model.save;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleBindingVO {
  @NotBlank(message = "{valid.common.required}")
  @Pattern(regexp = "^(ROLE_).+", message = "{valid.adminRole.id.mustStartWith.role}")
  private String id;

  @NotBlank(message = "{valid.common.required}")
  private String description;

  private Integer orderNo;
  private List<String> privilegeIds;
}
