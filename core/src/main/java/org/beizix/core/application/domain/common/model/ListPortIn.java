package org.beizix.core.application.domain.common.model;

public interface ListPortIn<T extends PageableListOutput<?>, E> {
  T connect(PageableInput pageableInput, E condition);
}
