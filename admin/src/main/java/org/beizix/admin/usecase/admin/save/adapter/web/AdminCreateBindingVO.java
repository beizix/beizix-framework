package org.beizix.admin.usecase.admin.save.adapter.web;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdminCreateBindingVO {
  private String id;
  private String password;

  @NotBlank(message = "{valid.common.required}")
  @Pattern(
      regexp =
          "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
      message = "{valid.common.email.wrong}")
  private String email;

  private Boolean accountDisabled;
  private Boolean accountLocked;
  private List<String> roleIds;
}
