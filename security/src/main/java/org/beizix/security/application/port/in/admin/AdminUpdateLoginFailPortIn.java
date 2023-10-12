package org.beizix.security.application.port.in.admin;

public interface AdminUpdateLoginFailPortIn {
  void connect(String id, Integer failCnt);
}
