package org.beizix.core.application.domain.operationlog.model.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationLogListInput {
  private String searchField;
  private String searchValue;
  private String searchAppType;
  private String searchOperationType;

  private Integer page;
  private Integer size;
  private String sort;
}
