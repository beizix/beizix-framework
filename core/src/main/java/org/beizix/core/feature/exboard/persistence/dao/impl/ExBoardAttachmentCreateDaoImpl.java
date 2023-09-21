package org.beizix.core.feature.exboard.persistence.dao.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.feature.exboard.application.model.ExBoardAttachment;
import org.beizix.core.feature.exboard.persistence.dao.ExBoardAttachmentCreateDao;
import org.beizix.core.feature.exboard.persistence.model.ExBoardAttachmentEntity;

@Repository
@RequiredArgsConstructor
class ExBoardAttachmentCreateDaoImpl implements ExBoardAttachmentCreateDao {
  private final ModelMapper modelMapper;
  private final ExBoardAttachmentRepo exBoardAttachmentRepo;

  @Override
  public ExBoardAttachment operate(ExBoardAttachment exBoardAttachment) {
    if (exBoardAttachment.getFileUploadInfo() == null) return null;
    ExBoardAttachmentEntity entity =
        exBoardAttachmentRepo.save(
            modelMapper.map(exBoardAttachment, ExBoardAttachmentEntity.class));
    return modelMapper.map(entity, ExBoardAttachment.class);
  }
}
