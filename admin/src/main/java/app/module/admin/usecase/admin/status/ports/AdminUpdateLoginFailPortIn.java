package app.module.admin.usecase.admin.status.ports;

public interface AdminUpdateLoginFailPortIn {
  void connect(String id, Integer failCnt);
}
