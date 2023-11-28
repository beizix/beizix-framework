package org.beizix.admin.usecase.admin.status.application.port.out;

public interface AdminUpdateLoginFailPortOut {
  void connect(String id, Integer failCnt);
}
