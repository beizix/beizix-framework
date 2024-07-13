package org.beizix.admin.usecase.admin.status.ports;

public interface AdminUpdateLoginFailPortOut {
  void connect(String id, Integer failCnt);
}
