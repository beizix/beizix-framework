package app.module.admin.usecase.uri.remove.adapter.web;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class URIRemoveVO {
  @NotEmpty(message = "{valid.common.required}")
  private String id;
}
