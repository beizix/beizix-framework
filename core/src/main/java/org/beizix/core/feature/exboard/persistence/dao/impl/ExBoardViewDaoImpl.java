package org.beizix.core.feature.exboard.persistence.dao.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.feature.exboard.application.model.ExBoard;
import org.beizix.core.feature.exboard.persistence.dao.ExBoardViewDao;

@Repository
@RequiredArgsConstructor
class ExBoardViewDaoImpl implements ExBoardViewDao {
  private final ExBoardRepo exBoardRepo;
  private final ModelMapper modelMapper;

  @Override
  public Optional<ExBoard> operate(Long id) {
    return exBoardRepo.findById(id).map(item -> modelMapper.map(item, ExBoard.class));
  }
}
