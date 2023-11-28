package org.beizix.core.usecase.exboard.view.application.port.in;

import org.beizix.core.config.application.component.AuditOutput;

public interface ExBoardViewPortIn<T extends AuditOutput> {
  T connect(Long id);
}
