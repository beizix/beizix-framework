package org.beizix.core.application.port.out.exboard;

import org.beizix.core.application.domain.common.model.ListPortOut;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;
import org.beizix.core.application.domain.exboard.model.list.ExBoardListOutput;

public interface ExBoardListPortOut
    extends ListPortOut<ExBoardListOutput, ExBoardListFilterInput> {}
