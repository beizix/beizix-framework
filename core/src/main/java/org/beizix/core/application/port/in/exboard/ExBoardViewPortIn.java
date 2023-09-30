package org.beizix.core.application.port.in.exboard;

import org.beizix.core.application.domain.exboard.model.view.ExBoardViewOutput;

public interface ExBoardViewPortIn {
  ExBoardViewOutput operate(Long id);
}
