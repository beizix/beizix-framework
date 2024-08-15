package app.module.admin.usecase.operationlog.list.adapters.web.model;

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
