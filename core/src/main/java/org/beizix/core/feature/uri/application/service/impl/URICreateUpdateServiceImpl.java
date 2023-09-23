package org.beizix.core.feature.uri.application.service.impl;

import java.io.IOException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.ContentDispositionType;
import org.beizix.core.config.enums.FileUploadType;
import org.beizix.core.config.exception.AlreadyExistItemException;
import org.beizix.core.application.port.in.fileupload.FileUploadPortIn;
import org.beizix.core.application.port.in.fileurl.FileUrlPortIn;
import org.beizix.core.feature.uri.application.model.URI;
import org.beizix.core.feature.uri.application.service.URICreateUpdateService;
import org.beizix.core.feature.uri.application.service.URIViewService;
import org.beizix.core.feature.uri.persistence.dao.URICreateUpdateDao;
import org.beizix.core.feature.uri.persistence.dao.URIMaxOrderNoDao;
import org.beizix.utility.common.MessageUtil;

@Service
@RequiredArgsConstructor
class URICreateUpdateServiceImpl implements URICreateUpdateService {
  private final URIMaxOrderNoDao uriMaxOrderNoDao;
  private final URICreateUpdateDao uriCreateUpdateDao;
  private final URIViewService uriViewService;
  private final MessageUtil messageUtil;
  private final FileUploadPortIn fileUploadPortIn;
  private final FileUrlPortIn fileUrlPortIn;

  @Transactional
  @CacheEvict(
      value = {"URITopItemCache", "URIItemsByAppTypeCache"},
      allEntries = true)
  @Override
  public URI operate(URI uri, boolean checkDuplicate) throws IOException {
    if (checkDuplicate) {
      // 중복 URI 정보 조회
      uriViewService
          .operate(uri.getAppType(), uri.getId())
          .ifPresent(
              item -> {
                throw new AlreadyExistItemException(
                    messageUtil.getMessage("valid.uri.already.exist", item.getId()));
              });
    }

    // order no 부여
    if (uri.getOrderNo() == null) uri.setOrderNo(uriMaxOrderNoDao.operate(uri.getParentId()) + 1);

    // og image 파일 업로드
    fileUploadPortIn
        .connect(FileUploadType.OG_IMAGE, uri.getOgImageFile())
        .ifPresent(
            postingFile ->
                uri.setOgImage(fileUrlPortIn.connect(ContentDispositionType.INLINE, postingFile)));

    return uriCreateUpdateDao.operate(uri);
  }
}
