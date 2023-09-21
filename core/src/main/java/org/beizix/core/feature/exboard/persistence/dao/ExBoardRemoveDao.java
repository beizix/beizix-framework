package org.beizix.core.feature.exboard.persistence.dao;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExBoardRemoveDao {
  void operate(List<Long> checkedIds);
}
