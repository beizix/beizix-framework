package org.beizix.core.feature.exboard.application.service;

import org.beizix.core.feature.exboard.application.model.ExBoard;

import java.io.IOException;

public interface ExBoardCreateUpdateService {
  ExBoard operate(ExBoard exBoard) throws IOException;
}
