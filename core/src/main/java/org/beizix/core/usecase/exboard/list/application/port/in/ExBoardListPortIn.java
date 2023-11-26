package org.beizix.core.usecase.exboard.list.application.port.in;

import org.beizix.core.application.domain.common.model.ListPortIn;
import org.beizix.core.usecase.exboard.list.domain.ExBoardListFilterCommand;
import org.beizix.core.usecase.exboard.list.domain.ExBoardPageableList;

public interface ExBoardListPortIn extends ListPortIn<ExBoardPageableList, ExBoardListFilterCommand> {}
