package org.beizix.core.config.application.component;

public interface ListPortIn<T extends PageableListOutput<?>, E> {
  T connect(PageableInput pageableInput, E condition);
}
