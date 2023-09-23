package org.beizix.core.application.domain.uicode.model;

import lombok.Data;

@Data
public class UICodeSortInput {
  private String id;
  private Integer orderNo;

  public UICodeSortInput(String id, Integer orderNo) {
    this.id = id;
    this.orderNo = orderNo;
  }
}
