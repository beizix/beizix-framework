package org.beizix.core.feature.exboard.persistence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.beizix.core.feature.exboard.application.model.ExBoard;
import org.beizix.core.feature.exboard.application.model.ExBoardListCondition;

public interface ExBoardListDao {
  Page<ExBoard> operate(Pageable pageable, ExBoardListCondition exBoardListCondition);
}
