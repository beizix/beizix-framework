package org.beizix.admin.usecases.uicode.save.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UICodeSaveCommand {
  private String id;
  private String parentId;
  private Integer orderNo;
  private String codeText;
  private String codeValue;
  private String msgCode;
  private Boolean inUse;
}
