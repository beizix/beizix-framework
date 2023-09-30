package org.beizix.core.application.port.out.exboard;

import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;

public interface ExBoardSavePortOut {
  ExBoardSaveInput connect(ExBoardSaveInput exBoard);
}
