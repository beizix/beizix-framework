package app.module.admin.usecase.uicode.save.adapter.web;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UICodeSaveVO {
  @NotBlank(message = "{valid.common.required}")
  @Pattern(regexp = "^.*[^\\\\.]$", message = "{valid.admin.id.notEndWith.dot}")
  @NonNull
  private String id;
  private String parentId;
  private Integer orderNo;
  @NotBlank(message = "{valid.common.required}")
  private String codeText;
  @NotBlank(message = "{valid.common.required}")
  private String codeValue;
  private String msgCode;
  private Boolean inUse;
  private String mode;
}
