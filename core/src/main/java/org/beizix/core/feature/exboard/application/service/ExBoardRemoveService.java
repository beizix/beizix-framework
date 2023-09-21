package org.beizix.core.feature.exboard.application.service;

import java.util.List;

public interface ExBoardRemoveService {
  void operate(List<Long> checkedIds);
}
