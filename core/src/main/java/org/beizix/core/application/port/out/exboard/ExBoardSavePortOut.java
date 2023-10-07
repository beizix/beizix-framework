package org.beizix.core.application.port.out.exboard;

import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveOutput;

public interface ExBoardSavePortOut {
  Long connect(ExBoardSaveInput exBoard);
}
