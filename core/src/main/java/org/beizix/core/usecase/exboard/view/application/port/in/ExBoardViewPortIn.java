package org.beizix.core.usecase.exboard.view.application.port.in;

import org.beizix.core.application.domain.common.model.AuditOutput;

public interface ExBoardViewPortIn<T extends AuditOutput> {
  T connect(Long id);
}
