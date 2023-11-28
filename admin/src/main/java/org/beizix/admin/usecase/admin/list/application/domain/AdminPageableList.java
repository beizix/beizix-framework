package org.beizix.admin.usecase.admin.list.application.domain;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.core.config.application.component.PageableListOutput;
import org.beizix.core.config.application.component.PageableOutput;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPageableList implements PageableListOutput<AdminElement>, Serializable {

  private PageableOutput pageable;
  private List<AdminElement> outputs;

  @Override
  public List<AdminElement> getContents() {
    return this.outputs;
  }

  @Override
  public PageableOutput getPageable() {
    return this.pageable;
  }
}
