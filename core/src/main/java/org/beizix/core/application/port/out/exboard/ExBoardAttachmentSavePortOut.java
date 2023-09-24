package org.beizix.core.application.port.out.exboard;

import org.beizix.core.application.domain.exboard.model.ExBoardAttachment;

public interface ExBoardAttachmentSavePortOut {
  ExBoardAttachment connect(ExBoardAttachment exBoard);
}
