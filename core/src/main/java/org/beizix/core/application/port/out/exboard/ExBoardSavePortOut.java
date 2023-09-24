package org.beizix.core.application.port.out.exboard;

import org.beizix.core.application.domain.exboard.model.ExBoardInput;

public interface ExBoardSavePortOut {
  ExBoardInput connect(ExBoardInput exBoard);
}
