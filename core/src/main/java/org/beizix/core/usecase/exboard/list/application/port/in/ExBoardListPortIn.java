package org.beizix.core.usecase.exboard.list.application.port.in;

import org.beizix.core.config.application.component.ListPortIn;
import org.beizix.core.usecase.exboard.list.application.domain.ExBoardListFilterCommand;
import org.beizix.core.usecase.exboard.list.application.domain.ExBoardPageableList;

public interface ExBoardListPortIn extends ListPortIn<ExBoardPageableList, ExBoardListFilterCommand> {}
