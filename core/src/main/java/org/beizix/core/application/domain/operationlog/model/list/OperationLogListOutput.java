package org.beizix.core.application.domain.operationlog.model.list;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.application.domain.common.model.PageableListOutput;
import org.beizix.core.application.domain.common.model.PageableOutput;

@Getter
@Setter
@AllArgsConstructor
public class OperationLogListOutput implements PageableListOutput<OperationLogOutput> {
  PageableOutput pageable;
  List<OperationLogOutput> contents;

  @Override
  public List<OperationLogOutput> getContents() {
    return contents;
  }

  @Override
  public PageableOutput getPageable() {
    return pageable;
  }
}
