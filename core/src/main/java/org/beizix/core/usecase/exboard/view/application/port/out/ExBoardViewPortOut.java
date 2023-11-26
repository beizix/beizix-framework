package org.beizix.core.usecase.exboard.view.application.port.out;


import java.util.Optional;
import org.beizix.core.application.domain.common.model.AuditOutput;

public interface ExBoardViewPortOut<T extends AuditOutput> {
  Optional<T> connect(Long id);
}
