package org.beizix.admin.usecase.uicode.save.application.port.out;

import java.util.Optional;

public interface UICodeMaxOrderNoPortOut {
  Optional<Integer> connect(String parentId);
}
