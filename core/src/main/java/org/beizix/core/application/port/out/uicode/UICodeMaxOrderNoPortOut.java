package org.beizix.core.application.port.out.uicode;

import java.util.Optional;

public interface UICodeMaxOrderNoPortOut {
  Optional<Integer> connect(String parentId);
}
