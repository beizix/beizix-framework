package org.beizix.admin.adapter.web.admin.model.view;

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
public class AdminViewRespVO {
  private String id;
  private String password;
  private String updatePassword;

  private String email;
  private boolean accountDisabled;
  private boolean accountLocked;
  private boolean editMode;
  private Set<String> roleIds;
  private OperationType operationType;
}
