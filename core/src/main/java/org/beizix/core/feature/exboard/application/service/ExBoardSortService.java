package org.beizix.core.feature.exboard.application.service;

import org.beizix.core.feature.exboard.application.model.ExBoard;

import java.util.List;

public interface ExBoardSortService {
  void operate(List<ExBoard> sortItems);
}
