package org.beizix.core.feature.exboard.persistence.dao;

import org.beizix.core.feature.exboard.application.model.ExBoard;

public interface ExBoardCreateUpdateDao {
  ExBoard operate(ExBoard exBoard);
}
