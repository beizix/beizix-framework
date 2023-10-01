package org.beizix.admin.adapter.web.exboard.model.remove;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExBoardRemoveReqVO {
  private List<Long> selectedItemIds;
}
