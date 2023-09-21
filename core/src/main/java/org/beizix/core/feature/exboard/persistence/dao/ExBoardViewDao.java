package org.beizix.core.feature.exboard.persistence.dao;

import org.beizix.core.feature.exboard.application.model.ExBoard;

import java.util.Optional;

public interface ExBoardViewDao {
  Optional<ExBoard> operate(Long id);
}
