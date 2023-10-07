package org.beizix.core.application.domain.exboard.model.list;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.beizix.core.application.domain.common.model.PageableListOutput;
import org.beizix.core.application.domain.common.model.PageableOutput;

@Getter
@Setter
@AllArgsConstructor
@Accessors(chain = true)
public class ExBoardListOutput implements PageableListOutput<ExBoardOutput> {
  private PageableOutput pageable;
  private List<ExBoardOutput> contents;
}
