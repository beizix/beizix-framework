package org.beizix.core.application.port.out.exboard;

import org.beizix.core.application.domain.exboard.model.ExBoardInput;

import java.util.Optional;

public interface ExBoardViewPortOut {
  Optional<ExBoardInput> connect(Long id);
}
