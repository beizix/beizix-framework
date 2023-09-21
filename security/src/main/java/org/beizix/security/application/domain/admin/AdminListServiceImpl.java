package org.beizix.security.application.domain.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.beizix.security.application.domain.admin.model.list.AdminListResp;
import org.beizix.security.application.domain.admin.model.query.AdminListReq;
import org.beizix.security.application.port.in.admin.AdminListService;
import org.beizix.security.application.port.out.admin.AdminListDao;

@Service
@RequiredArgsConstructor
@Slf4j
class AdminListServiceImpl implements AdminListService {
  private final AdminListDao adminListDao;

  @Override
  public Page<AdminListResp> operate(
      Pageable pageable, AdminListReq listReq) {
    return adminListDao.operate(pageable, listReq);
  }
}
