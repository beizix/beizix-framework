package org.beizix.core.application.port.in.exboard;

import org.beizix.core.application.domain.common.model.ListPortIn;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;
import org.beizix.core.application.domain.exboard.model.list.ExBoardListOutput;

public interface ExBoardListPortIn extends ListPortIn<ExBoardListOutput, ExBoardListFilterInput> {}
