package org.beizix.admin.usecase.exboard.remove.adapter.web;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExBoardRemoveVO {
  private List<Long> selectedItemIds;
}
