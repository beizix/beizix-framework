package org.beizix.core.config.application.component;

public interface ListPortOut<T extends PageableListOutput<?>, E> {
  T connect(PageableInput pageableInput, E condition);
}
