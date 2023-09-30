package org.beizix.core.adapter.persistence.exboard;

import org.beizix.core.adapter.persistence.exboard.repository.ExBoardAttachmentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.port.out.exboard.ExBoardAttachmentSavePortOut;
import org.beizix.core.adapter.persistence.exboard.model.ExBoardAttachment;

@Repository
@RequiredArgsConstructor
class ExBoardAttachmentSaveDao implements ExBoardAttachmentSavePortOut {
  private final ModelMapper modelMapper;
  private final ExBoardAttachmentRepo exBoardAttachmentRepo;

  @Override
  public org.beizix.core.application.domain.exboard.model.ExBoardAttachment connect(
      org.beizix.core.application.domain.exboard.model.ExBoardAttachment exBoardAttachment) {
    if (exBoardAttachment.getFileUploadInfo() == null) return null;
    ExBoardAttachment entity =
        exBoardAttachmentRepo.save(
            modelMapper.map(exBoardAttachment, ExBoardAttachment.class));
    return modelMapper.map(entity, org.beizix.core.application.domain.exboard.model.ExBoardAttachment.class);
  }
}
