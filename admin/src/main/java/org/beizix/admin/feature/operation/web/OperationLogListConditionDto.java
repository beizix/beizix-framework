package org.beizix.admin.feature.operation.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationLogListConditionDto {
  private String searchField;
  private String searchValue;
  private String searchAppType;
  private String searchOperationType;

  private Integer page;
  private Integer size;
  private String sort;
}
