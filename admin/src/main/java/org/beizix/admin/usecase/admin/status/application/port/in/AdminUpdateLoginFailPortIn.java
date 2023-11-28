package org.beizix.admin.usecase.admin.status.application.port.in;

public interface AdminUpdateLoginFailPortIn {
  void connect(String id, Integer failCnt);
}
