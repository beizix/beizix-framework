package org.beizix.admin.adapter.web.operation.model.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationLogListStatusVO {
  private String searchField;
  private String searchValue;
  private String searchAppType;
  private String searchOperationType;

  private Integer page;
  private Integer size;
  private String sort;
}
