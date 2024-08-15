package app.module.admin.usecase.admin.status.ports;

public interface AdminUpdateAccountLockPortIn {
  void connect(String id, boolean accountLocked);
}
