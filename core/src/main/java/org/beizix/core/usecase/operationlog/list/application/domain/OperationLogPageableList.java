package org.beizix.core.usecase.operationlog.list.application.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.config.application.component.PageableListOutput;
import org.beizix.core.config.application.component.PageableOutput;

@Getter
@Setter
@AllArgsConstructor
public class OperationLogPageableList implements PageableListOutput<OperationLogElement> {
  PageableOutput pageable;
  List<OperationLogElement> contents;

  @Override
  public List<OperationLogElement> getContents() {
    return contents;
  }

  @Override
  public PageableOutput getPageable() {
    return pageable;
  }
}
