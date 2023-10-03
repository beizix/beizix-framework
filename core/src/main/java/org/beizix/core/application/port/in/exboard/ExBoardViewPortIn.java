package org.beizix.core.application.port.in.exboard;

import org.beizix.core.application.domain.common.model.AuditOutput;

public interface ExBoardViewPortIn<T extends AuditOutput> {
  T connect(Long id);
}
