package org.beizix.core.feature.uicode.application.service;

import org.beizix.core.feature.uicode.application.model.UICode;

import java.util.List;

public interface UICodeListService {
  List<UICode> operate(String parentId);
}
