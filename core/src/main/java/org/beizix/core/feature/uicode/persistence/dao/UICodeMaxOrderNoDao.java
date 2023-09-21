package org.beizix.core.feature.uicode.persistence.dao;

import java.util.Optional;

public interface UICodeMaxOrderNoDao {
  Optional<Integer> operate(String parentId);
}
