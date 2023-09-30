package org.beizix.core.adapter.persistence.exboard;

import lombok.RequiredArgsConstructor;
import org.beizix.core.adapter.persistence.exboard.model.ExBoardAttachment;
import org.beizix.core.adapter.persistence.exboard.repository.ExBoardAttachmentRepo;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveAttachInput;
import org.beizix.core.application.port.out.exboard.ExBoardAttachmentSavePortOut;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ExBoardAttachmentSaveDao implements ExBoardAttachmentSavePortOut {
  private final ModelMapper modelMapper;
  private final ExBoardAttachmentRepo exBoardAttachmentRepo;

  @Override
  public ExBoardSaveAttachInput connect(ExBoardSaveAttachInput exBoardAttachment) {
    if (exBoardAttachment.getFileUploadOutput() == null) return null;
    ExBoardAttachment entity =
        exBoardAttachmentRepo.save(modelMapper.map(exBoardAttachment, ExBoardAttachment.class));
    return modelMapper.map(entity, ExBoardSaveAttachInput.class);
  }
}
