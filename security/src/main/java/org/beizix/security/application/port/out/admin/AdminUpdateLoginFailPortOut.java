package org.beizix.security.application.port.out.admin;

public interface AdminUpdateLoginFailPortOut {
  void connect(String id, Integer failCnt);
}
