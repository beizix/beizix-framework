package org.beizix.core.adapter.persistence.exboard;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.beizix.core.adapter.persistence.exboard.repository.ExBoardRepo;
import org.beizix.core.application.domain.exboard.model.view.ExBoardViewOutput;
import org.beizix.core.application.port.out.exboard.ExBoardViewPortOut;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ExBoardViewDao implements ExBoardViewPortOut {
  private final ExBoardRepo exBoardRepo;
  private final ModelMapper modelMapper;

  @Override
  public Optional<ExBoardViewOutput> connect(Long id) {
    return exBoardRepo.findById(id).map(item -> modelMapper.map(item, ExBoardViewOutput.class));
  }
}
