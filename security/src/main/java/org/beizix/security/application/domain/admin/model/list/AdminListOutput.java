package org.beizix.security.application.domain.admin.model.list;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.core.application.domain.common.model.PageableListOutput;
import org.beizix.core.application.domain.common.model.PageableOutput;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminListOutput implements PageableListOutput<AdminOutput>, Serializable {

  private PageableOutput pageable;
  private List<AdminOutput> outputs;

  @Override
  public List<AdminOutput> getContents() {
    return this.outputs;
  }

  @Override
  public PageableOutput getPageable() {
    return this.pageable;
  }
}
