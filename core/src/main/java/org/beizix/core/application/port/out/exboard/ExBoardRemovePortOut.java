package org.beizix.core.application.port.out.exboard;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExBoardRemovePortOut {
  void connect(List<Long> checkedIds);
}
