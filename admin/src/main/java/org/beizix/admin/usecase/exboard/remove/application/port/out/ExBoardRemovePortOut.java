package org.beizix.admin.usecase.exboard.remove.application.port.out;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExBoardRemovePortOut {
  void connect(List<Long> checkedIds);
}
