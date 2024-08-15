package app.module.admin.config.adapter.persistence.initdata;

import app.module.admin.usecase.privilege.save.adapters.persistence.PrivilegeSaveRepo;
import lombok.RequiredArgsConstructor;
import app.module.admin.config.adapter.persistence.entity.Privilege;
import app.module.core.config.application.util.PropertyUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class InitPrivilegeData implements ApplicationRunner {
  private final PrivilegeSaveRepo privilegeRepo;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (!PropertyUtil.isCoreDataRequired()) return;

    privilegeRepo.save(new Privilege("READ", "읽기 권한", 0));

    privilegeRepo.save(new Privilege("WRITE", "쓰기 권한", 1));

    privilegeRepo.save(new Privilege("DELETE", "삭제 권한", 2));

    privilegeRepo.save(new Privilege("UPDATE", "수정 권한", 3));

    privilegeRepo.save(new Privilege("DOWNLOAD", "다운로드 권한", 4));

    privilegeRepo.save(new Privilege("PRIVATE_DOWNLOAD", "비공개 파일접근 권한", 5));
  }
}
