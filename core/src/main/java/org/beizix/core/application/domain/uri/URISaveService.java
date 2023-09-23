package org.beizix.core.application.domain.uri;

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
import org.beizix.core.application.domain.uri.model.URIInput;
import org.beizix.core.application.port.in.uri.URISavePortIn;
import org.beizix.core.application.port.in.uri.URIViewPortIn;
import org.beizix.core.application.port.out.uri.URISavePortOut;
import org.beizix.core.application.port.out.uri.URIMaxOrderNoPortOut;
import org.beizix.utility.common.MessageUtil;

@Service
@RequiredArgsConstructor
class URISaveService implements URISavePortIn {
  private final URIMaxOrderNoPortOut uriMaxOrderNoPortOut;
  private final URISavePortOut uriSavePortOut;
  private final URIViewPortIn uriViewPortIn;
  private final MessageUtil messageUtil;
  private final FileUploadPortIn fileUploadPortIn;
  private final FileUrlPortIn fileUrlPortIn;

  @Transactional
  @CacheEvict(
      value = {"URITopItemCache", "URIItemsByAppTypeCache"},
      allEntries = true)
  @Override
  public URIInput connect(URIInput uri, boolean checkDuplicate) throws IOException {
    if (checkDuplicate) {
      // 중복 URI 정보 조회
      uriViewPortIn
          .connect(uri.getAppType(), uri.getId())
          .ifPresent(
              item -> {
                throw new AlreadyExistItemException(
                    messageUtil.getMessage("valid.uri.already.exist", item.getId()));
              });
    }

    // order no 부여
    if (uri.getOrderNo() == null) uri.setOrderNo(uriMaxOrderNoPortOut.connect(uri.getParentId()) + 1);

    // og image 파일 업로드
    fileUploadPortIn
        .connect(FileUploadType.OG_IMAGE, uri.getOgImageFile())
        .ifPresent(
            postingFile ->
                uri.setOgImage(fileUrlPortIn.connect(ContentDispositionType.INLINE, postingFile)));

    return uriSavePortOut.connect(uri);
  }
}
