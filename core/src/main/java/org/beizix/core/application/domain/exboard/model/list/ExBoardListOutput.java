package org.beizix.core.application.domain.exboard.model.list;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.beizix.core.application.domain.common.model.PageableBase;
import org.beizix.core.application.domain.common.model.PageableListOutput;

@Getter
@Setter
@AllArgsConstructor
@Accessors(chain = true)
public class ExBoardListOutput implements PageableListOutput {
  private long totalElements;
  private int totalPages;
  private PageableBase pageable;
  private List<ExBoardListItem> contents;
}
