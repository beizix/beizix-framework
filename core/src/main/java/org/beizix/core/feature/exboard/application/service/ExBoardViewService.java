package org.beizix.core.feature.exboard.application.service;

import org.beizix.core.feature.exboard.application.model.ExBoard;

import java.util.Optional;

public interface ExBoardViewService {
  ExBoard operate(Long id);
}
