package org.beizix.core.application.domain.exboard.model.list;

import java.util.List;
import lombok.AllArgsConstructor;
import org.beizix.core.application.domain.common.model.PageableListOutput;
import org.beizix.core.application.domain.common.model.PageableOutput;

@AllArgsConstructor
public class ExBoardListOutput implements PageableListOutput<ExBoardOutput> {
  private final PageableOutput pageable;
  private final List<ExBoardOutput> contents;

  @Override
  public List<ExBoardOutput> getContents() {
    return this.contents;
  }

  @Override
  public PageableOutput getPageable() {
    return this.pageable;
  }
}
