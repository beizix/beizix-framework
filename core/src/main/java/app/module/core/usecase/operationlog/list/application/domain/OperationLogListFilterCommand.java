package app.module.core.usecase.operationlog.list.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationLogListFilterCommand {
  private String searchField;
  private String searchValue;
  private String searchAppType;
  private String searchOperationType;
}
