package org.beizix.core.feature.operationlog.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationLogListCondition {
  private String searchField;
  private String searchValue;
  private String searchAppType;
  private String searchOperationType;

  private Integer page;
  private Integer size;
  private String sort;
}
