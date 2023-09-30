package org.beizix.core.application.port.out.exboard;

import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveAttachInput;

public interface ExBoardAttachmentSavePortOut {
  ExBoardSaveAttachInput connect(ExBoardSaveAttachInput exBoard);
}
