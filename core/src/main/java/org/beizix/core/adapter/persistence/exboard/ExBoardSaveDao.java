package org.beizix.core.adapter.persistence.exboard;

import lombok.RequiredArgsConstructor;
import org.beizix.core.adapter.persistence.exboard.model.ExBoard;
import org.beizix.core.adapter.persistence.exboard.repository.ExBoardRepo;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveOutput;
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
  public ExBoardSaveOutput connect(ExBoardSaveInput saveInput) {

    // 수정시, 화면에 입력값이 없어서 null 로 업데이트 되는 문제 방지
    if (saveInput.getId() != null)
      exBoardViewPortOut
          .connect(saveInput.getId())
          .ifPresent(
              item -> {
                if (saveInput.getRepresentImage() == null)
                  saveInput.setRepresentImage(item.getRepresentImage());
                if (saveInput.getPrivateAttachment() == null)
                  saveInput.setPrivateAttachment(item.getPrivateAttachment());
              });

    ExBoard entity = exBoardRepo.save(modelMapper.map(saveInput, ExBoard.class));
    return modelMapper.map(entity, ExBoardSaveOutput.class);
  }
}
