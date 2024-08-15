package app.module.admin.usecase.admin.status.ports;

public interface AdminUpdateAccountLockPortOut {
  void connect(String id, boolean accountLocked);
}
