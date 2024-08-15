package app.module.core.usecase.exboard.view.application.port.out;


import java.util.Optional;
import app.module.core.config.application.component.AuditOutput;

public interface ExBoardViewPortOut<T extends AuditOutput> {
  Optional<T> connect(Long id);
}
