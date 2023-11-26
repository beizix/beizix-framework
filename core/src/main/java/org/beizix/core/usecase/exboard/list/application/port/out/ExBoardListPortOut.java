package org.beizix.core.usecase.exboard.list.application.port.out;

import org.beizix.core.application.domain.common.model.ListPortOut;
import org.beizix.core.usecase.exboard.list.application.domain.ExBoardListFilterCommand;
import org.beizix.core.usecase.exboard.list.application.domain.ExBoardPageableList;

public interface ExBoardListPortOut
    extends ListPortOut<ExBoardPageableList, ExBoardListFilterCommand> {}
