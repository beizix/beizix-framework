package org.beizix.admin.usecase.admin.status.application.port.out;

public interface AdminUpdateAccountLockPortOut {
  void connect(String id, boolean accountLocked);
}
