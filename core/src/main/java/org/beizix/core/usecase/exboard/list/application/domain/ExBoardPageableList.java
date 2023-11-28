package org.beizix.core.usecase.exboard.list.application.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import org.beizix.core.config.application.component.PageableListOutput;
import org.beizix.core.config.application.component.PageableOutput;

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
