package org.beizix.admin.adapter.web.admin.model.save;

import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.utility.enums.OperationType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminSaveReqVO {
  @NotBlank(message = "{valid.common.required}")
  private String id;

  private String password;
  private String updatePassword;

  @NotBlank(message = "{valid.common.required}")
  @Pattern(
      regexp =
          "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
      message = "{valid.common.email.wrong}")
  private String email;

  private boolean accountDisabled;

  private boolean accountLocked;

  private boolean editMode;

  private Set<String> roleIds;

  private OperationType operationType;
}
