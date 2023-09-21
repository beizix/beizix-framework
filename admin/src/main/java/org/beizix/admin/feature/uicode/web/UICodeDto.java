package org.beizix.admin.feature.uicode.web;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class UICodeDto {
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

  private List<UICodeDto> updateList;
}
