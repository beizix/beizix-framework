package org.beizix.admin.usecase.admin.status.application.port.in;

public interface AdminUpdateAccountLockPortIn {
  void connect(String id, boolean accountLocked);
}
