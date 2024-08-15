package app.module.admin.usecase.exboard.remove.adapters.web.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExBoardRemoveVO {
  private List<Long> selectedItemIds;
}
