package org.beizix.core.feature.uicode.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UICode {
  private String id;
  private String parentId;
  private Integer orderNo;
  private String codeText;
  private String codeValue;
  private String msgCode;
  private Boolean inUse;
  private List<UICode> nodes;
  private Integer depth;

  public UICode(
      String id,
      String parentId,
      Integer orderNo,
      String codeText,
      String codeValue,
      String msgCode,
      Boolean inUse,
      List<UICode> nodes,
      Integer depth) {
    this.id = id;
    this.parentId = parentId;
    this.orderNo = orderNo;
    this.codeText = codeText;
    this.codeValue = codeValue;
    this.msgCode = msgCode;
    this.inUse = inUse;
    this.nodes = nodes;
    this.depth = depth;
  }
}
