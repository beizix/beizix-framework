package org.beizix.core.application.domain.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@Accessors(chain = true)
public class PageableBase {
  private Boolean previous;
  private Integer pageNumber;
  private Integer pageSize;
  private String sortField;
  private String sortDirection;

  public Boolean hasPrevious() {
    return this.getPrevious();
  }
}
