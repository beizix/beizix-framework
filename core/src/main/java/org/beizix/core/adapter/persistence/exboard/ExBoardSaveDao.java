package org.beizix.core.adapter.persistence.exboard;

import lombok.RequiredArgsConstructor;
import org.beizix.core.adapter.persistence.exboard.model.ExBoard;
import org.beizix.core.adapter.persistence.exboard.repository.ExBoardRepo;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveOutput;
import org.beizix.core.application.port.out.exboard.ExBoardSavePortOut;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ExBoardSaveDao implements ExBoardSavePortOut {
  private final ExBoardRepo exBoardRepo;
  private final ModelMapper modelMapper;

  @Override
  public ExBoardSaveOutput connect(ExBoardSaveInput saveInput) {
    ExBoard entity = exBoardRepo.save(modelMapper.map(saveInput, ExBoard.class));
    return modelMapper.map(entity, ExBoardSaveOutput.class);
  }
}
