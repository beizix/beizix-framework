package org.beizix.core.adapter.persistence.exboard;

import lombok.RequiredArgsConstructor;
import org.beizix.core.adapter.persistence.exboard.model.ExBoard;
import org.beizix.core.adapter.persistence.exboard.repository.ExBoardRepo;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;
import org.beizix.core.application.port.out.exboard.ExBoardSavePortOut;
import org.beizix.core.application.port.out.exboard.ExBoardViewPortOut;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ExBoardSaveDao implements ExBoardSavePortOut {
  private final ExBoardRepo exBoardRepo;
  private final ModelMapper modelMapper;
  private final ExBoardViewPortOut exBoardViewPortOut;

  @Override
  public ExBoardSaveInput connect(ExBoardSaveInput exBoard) {

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

    ExBoard entity = exBoardRepo.save(modelMapper.map(exBoard, ExBoard.class));
    return modelMapper.map(entity, ExBoardSaveInput.class);
  }
}
