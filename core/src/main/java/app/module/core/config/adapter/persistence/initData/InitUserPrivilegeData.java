package app.module.core.config.adapter.persistence.initData;

import app.module.core.config.application.util.PropertyUtil;
import lombok.RequiredArgsConstructor;
import app.module.core.usecase.user.createPrivilege.ports.CreatePrivilegePortIn;
import app.module.core.usecase.user.createPrivilege.ports.application.domain.CreatePrivilegeCmd;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class InitUserPrivilegeData implements ApplicationRunner {
  private final CreatePrivilegePortIn createPrivilegePortIn;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (!PropertyUtil.isCoreDataRequired()) return;

    createPrivilegePortIn.operate(new CreatePrivilegeCmd("READ", "읽기 권한", 0));
    createPrivilegePortIn.operate(new CreatePrivilegeCmd("WRITE", "쓰기 권한", 1));
    createPrivilegePortIn.operate(new CreatePrivilegeCmd("DELETE", "삭제 권한", 2));
    createPrivilegePortIn.operate(new CreatePrivilegeCmd("UPDATE", "수정 권한", 3));
    createPrivilegePortIn.operate(new CreatePrivilegeCmd("DOWNLOAD", "다운로드 권한", 4));
    createPrivilegePortIn.operate(new CreatePrivilegeCmd("PRIVATE_DOWNLOAD", "비공개 파일접근 권한", 5));
  }
}
