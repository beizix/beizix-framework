package app.module.admin.usecase.admin.remove.ports;

import java.util.List;

public interface AdminRemovePortIn {
  void connect(List<String> checkedIds);
}
