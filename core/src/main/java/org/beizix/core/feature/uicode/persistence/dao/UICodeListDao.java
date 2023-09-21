package org.beizix.core.feature.uicode.persistence.dao;

import org.beizix.core.feature.uicode.application.model.UICode;

import java.util.List;

public interface UICodeListDao {
  List<UICode> operate(String parentId);
}
