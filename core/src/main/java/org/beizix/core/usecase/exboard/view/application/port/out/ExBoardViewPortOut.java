package org.beizix.core.usecase.exboard.view.application.port.out;


import java.util.Optional;
import org.beizix.core.config.application.component.AuditOutput;

public interface ExBoardViewPortOut<T extends AuditOutput> {
  Optional<T> connect(Long id);
}
