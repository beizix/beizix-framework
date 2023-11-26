package org.beizix.admin.usecase.operationlog.list.adapter.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OperationLogListFilterVO {
  private String searchField;
  private String searchValue;
  private String searchAppType;
  private String searchOperationType;
}
