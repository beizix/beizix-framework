package org.beizix.core.application.domain.common.model;

public interface PageableListOutput {
  long getTotalElements();
  int getTotalPages();
  PageableBase getPageable();
}
