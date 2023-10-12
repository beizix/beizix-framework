package org.beizix.security.application.port.out.admin;

public interface AdminUpdateAccountLockPortOut {
  void connect(String id, boolean accountLocked);
}
