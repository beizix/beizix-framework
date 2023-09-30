package org.beizix.core.application.port.in.exboard;

import java.io.IOException;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveOutput;

public interface ExBoardSavePortIn {
  ExBoardSaveOutput connect(ExBoardSaveInput exBoard) throws IOException;
}
