package org.beizix.core.adapter.persistence.exboard;

import java.util.Optional;

import org.beizix.core.adapter.persistence.exboard.repository.ExBoardRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.exboard.model.ExBoardInput;
import org.beizix.core.application.port.out.exboard.ExBoardViewPortOut;

@Repository
@RequiredArgsConstructor
class ExBoardViewDao implements ExBoardViewPortOut {
  private final ExBoardRepo exBoardRepo;
  private final ModelMapper modelMapper;

  @Override
  public Optional<ExBoardInput> connect(Long id) {
    return exBoardRepo.findById(id).map(item -> modelMapper.map(item, ExBoardInput.class));
  }
}
