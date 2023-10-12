package org.beizix.security.application.port.in.admin;

public interface AdminUpdateAccountLockPortIn {
  void connect(String id, boolean accountLocked);
}
