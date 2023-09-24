package org.beizix.core.adapter.persistence.exboard;

import org.beizix.core.adapter.persistence.exboard.repository.ExBoardAttachmentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.exboard.model.ExBoardAttachment;
import org.beizix.core.application.port.out.exboard.ExBoardAttachmentSavePortOut;
import org.beizix.core.adapter.persistence.exboard.model.ExBoardAttachmentEntity;

@Repository
@RequiredArgsConstructor
class ExBoardAttachmentSaveDao implements ExBoardAttachmentSavePortOut {
  private final ModelMapper modelMapper;
  private final ExBoardAttachmentRepo exBoardAttachmentRepo;

  @Override
  public ExBoardAttachment connect(ExBoardAttachment exBoardAttachment) {
    if (exBoardAttachment.getFileUploadInfo() == null) return null;
    ExBoardAttachmentEntity entity =
        exBoardAttachmentRepo.save(
            modelMapper.map(exBoardAttachment, ExBoardAttachmentEntity.class));
    return modelMapper.map(entity, ExBoardAttachment.class);
  }
}
