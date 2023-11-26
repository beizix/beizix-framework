package org.beizix.core.application.domain.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.configuration.application.enums.OrderDir;

@Getter
@Setter
@AllArgsConstructor
public class PageableOutput {
  private final Boolean previous;
  private final Integer pageNumber;
  private final Integer pageSize;
  private final String orderBy;
  private final OrderDir orderDir;
  private final Long totalElements;
  private final Integer totalPages;

  public Boolean getNext() {
    return this.getPageNumber() < this.getTotalPages() - 1;
  }
}
