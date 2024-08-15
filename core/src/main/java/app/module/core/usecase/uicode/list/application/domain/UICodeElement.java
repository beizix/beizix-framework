package app.module.core.usecase.uicode.list.application.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UICodeElement {
  private String id;
  private String parentId;
  private Integer orderNo;
  private String codeText;
  private String codeValue;
  private String msgCode;
  private Boolean inUse;
}
