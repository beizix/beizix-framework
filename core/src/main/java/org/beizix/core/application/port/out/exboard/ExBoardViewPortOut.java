package org.beizix.core.application.port.out.exboard;


import java.util.Optional;
import org.beizix.core.application.domain.exboard.model.view.ExBoardViewOutput;

public interface ExBoardViewPortOut {
  Optional<ExBoardViewOutput> connect(Long id);
}
