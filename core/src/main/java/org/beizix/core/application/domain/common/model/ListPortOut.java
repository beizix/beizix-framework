package org.beizix.core.application.domain.common.model;

public interface ListPortOut<T extends PageableListOutput<?>, E> {
  T connect(PageableInput pageableInput, E condition);
}
