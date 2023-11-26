package org.beizix.core.usecase.exboard.list.application.port.out;

import org.beizix.core.application.domain.common.model.ListPortOut;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;
import org.beizix.core.usecase.exboard.list.domain.ExBoardPageableList;

public interface ExBoardListPortOut
    extends ListPortOut<ExBoardPageableList, ExBoardListFilterInput> {}
