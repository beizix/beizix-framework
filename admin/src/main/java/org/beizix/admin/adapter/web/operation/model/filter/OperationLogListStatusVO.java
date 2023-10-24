package org.beizix.admin.adapter.web.operation.model.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OperationLogListStatusVO {
  private String searchField;
  private String searchValue;
  private String searchAppType;
  private String searchOperationType;
}
