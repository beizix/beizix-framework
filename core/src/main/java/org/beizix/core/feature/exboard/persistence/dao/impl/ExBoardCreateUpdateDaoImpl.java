package org.beizix.core.feature.exboard.persistence.dao.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.feature.exboard.application.model.ExBoard;
import org.beizix.core.feature.exboard.persistence.dao.ExBoardCreateUpdateDao;
import org.beizix.core.feature.exboard.persistence.dao.ExBoardViewDao;
import org.beizix.core.feature.exboard.persistence.model.ExBoardEntity;

@Repository
@RequiredArgsConstructor
class ExBoardCreateUpdateDaoImpl implements ExBoardCreateUpdateDao {
  private final ExBoardRepo exBoardRepo;
  private final ModelMapper modelMapper;
  private final ExBoardViewDao exBoardViewDao;

  @Override
  public ExBoard operate(ExBoard exBoard) {

    // 수정시, 화면에 입력값이 없어서 null 로 업데이트 되는 문제 방지
    if (exBoard.getId() != null)
      exBoardViewDao
          .operate(exBoard.getId())
          .ifPresent(
              item -> {
                if (exBoard.getRepresentImage() == null)
                  exBoard.setRepresentImage(item.getRepresentImage());
                if (exBoard.getPrivateAttachment() == null)
                  exBoard.setPrivateAttachment(item.getPrivateAttachment());
              });

    ExBoardEntity entity = exBoardRepo.save(modelMapper.map(exBoard, ExBoardEntity.class));
    return modelMapper.map(entity, ExBoard.class);
  }
}
