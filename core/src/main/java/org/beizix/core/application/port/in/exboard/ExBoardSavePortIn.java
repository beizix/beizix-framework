package org.beizix.core.application.port.in.exboard;

import org.beizix.core.application.domain.exboard.model.ExBoardInput;

import java.io.IOException;

public interface ExBoardSavePortIn {
  ExBoardInput connect(ExBoardInput exBoard) throws IOException;
}
