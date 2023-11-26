package org.beizix.admin.usecase.uicode.view.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UICodeView {
  private String id;
  private String parentId;
  private Integer orderNo;
  private String codeText;
  private String codeValue;
  private String msgCode;
  private Boolean inUse;
}
