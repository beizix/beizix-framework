package org.beizix.core.adapter.persistence.exboard;

import org.beizix.core.adapter.persistence.exboard.repository.ExBoardRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.exboard.model.ExBoardInput;
import org.beizix.core.application.port.out.exboard.ExBoardSavePortOut;
import org.beizix.core.application.port.out.exboard.ExBoardViewPortOut;
import org.beizix.core.adapter.persistence.exboard.model.ExBoardEntity;

@Repository
@RequiredArgsConstructor
class ExBoardSaveDao implements ExBoardSavePortOut {
  private final ExBoardRepo exBoardRepo;
  private final ModelMapper modelMapper;
  private final ExBoardViewPortOut exBoardViewPortOut;

  @Override
  public ExBoardInput connect(ExBoardInput exBoard) {

    // 수정시, 화면에 입력값이 없어서 null 로 업데이트 되는 문제 방지
    if (exBoard.getId() != null)
      exBoardViewPortOut
          .connect(exBoard.getId())
          .ifPresent(
              item -> {
                if (exBoard.getRepresentImage() == null)
                  exBoard.setRepresentImage(item.getRepresentImage());
                if (exBoard.getPrivateAttachment() == null)
                  exBoard.setPrivateAttachment(item.getPrivateAttachment());
              });

    ExBoardEntity entity = exBoardRepo.save(modelMapper.map(exBoard, ExBoardEntity.class));
    return modelMapper.map(entity, ExBoardInput.class);
  }
}
