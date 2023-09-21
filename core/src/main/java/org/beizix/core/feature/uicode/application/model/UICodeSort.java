package org.beizix.core.feature.uicode.application.model;

import lombok.Data;

@Data
public class UICodeSort {
  private String id;
  private Integer orderNo;

  public UICodeSort(String id, Integer orderNo) {
    this.id = id;
    this.orderNo = orderNo;
  }
}
