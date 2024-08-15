package app.module.admin.usecase.exboard.remove.ports;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExBoardRemovePortOut {
  void connect(List<Long> checkedIds);
}
