package org.beizix.core.usecase.exboard.list.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import org.beizix.core.application.domain.common.model.PageableListOutput;
import org.beizix.core.application.domain.common.model.PageableOutput;

@AllArgsConstructor
public class ExBoardPageableList implements PageableListOutput<ExBoardElement> {
  private final PageableOutput pageable;
  private final List<ExBoardElement> contents;

  @Override
  public List<ExBoardElement> getContents() {
    return this.contents;
  }

  @Override
  public PageableOutput getPageable() {
    return this.pageable;
  }
}
