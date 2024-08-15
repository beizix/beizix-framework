package app.module.core.usecase.exboard.view.application.port.in;

import app.module.core.config.application.component.AuditOutput;

public interface ExBoardViewPortIn<T extends AuditOutput> {
  T connect(Long id);
}
