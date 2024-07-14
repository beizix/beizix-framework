package org.beizix.admin.config.application.initdata;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.privilege.save.adapters.persistence.PrivilegeSaveRepo;
import org.beizix.admin.config.adapter.persistence.entity.Privilege;
import org.beizix.core.config.application.util.PropertyUtil;
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

    privilegeRepo.save(Privilege.builder().id("READ").description("읽기 권한").orderNo(0).build());

    privilegeRepo.save(Privilege.builder().id("WRITE").description("쓰기 권한").orderNo(1).build());

    privilegeRepo.save(Privilege.builder().id("DELETE").description("삭제 권한").orderNo(2).build());

    privilegeRepo.save(Privilege.builder().id("UPDATE").description("수정 권한").orderNo(3).build());

    privilegeRepo.save(
        Privilege.builder().id("DOWNLOAD").description("다운로드 권한").orderNo(4).build());

    privilegeRepo.save(
        Privilege.builder().id("PRIVATE_DOWNLOAD").description("비공개 파일 접근 권한").orderNo(5).build());
  }
}
