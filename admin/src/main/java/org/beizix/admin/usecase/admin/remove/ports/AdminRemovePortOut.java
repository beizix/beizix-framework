package org.beizix.admin.usecase.admin.remove.ports;

import java.util.List;

public interface AdminRemovePortOut {
  void connect(List<String> checkedIds);
}
