package org.beizix.core.application.port.in.exboard;


import java.io.IOException;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;

public interface ExBoardSavePortIn {
  ExBoardSaveInput connect(ExBoardSaveInput exBoard) throws IOException;
}
