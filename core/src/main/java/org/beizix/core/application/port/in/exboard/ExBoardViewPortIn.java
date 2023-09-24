package org.beizix.core.application.port.in.exboard;

import org.beizix.core.application.domain.exboard.model.ExBoardInput;

public interface ExBoardViewPortIn {
  ExBoardInput operate(Long id);
}
