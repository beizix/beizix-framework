package org.beizix.core.usecase.uri.save.application.port.in;

import java.io.IOException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.core.usecase.uri.save.application.domain.URISaveCommand;
import org.beizix.core.usecase.file.upload.application.port.in.FileUploadPortIn;
import org.beizix.core.usecase.file.url.application.port.in.FileUrlPortIn;
import org.beizix.core.usecase.uri.save.application.port.out.URIMaxOrderNoPortOut;
import org.beizix.core.usecase.uri.save.application.port.out.URISavePortOut;
import org.beizix.core.config.application.enums.ContentDispositionType;
import org.beizix.core.config.application.enums.FileUploadType;
import org.beizix.core.config.application.exception.AlreadyExistItemException;
import org.beizix.core.usecase.uri.view.application.port.in.URIViewPortIn;
import org.beizix.core.config.application.util.MessageUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
      value = {"URIItemsByAppTypeCache", "URITopTierCache"},
      allEntries = true)
  @Override
  public String connect(URISaveCommand uri, MultipartFile ogImageFile, boolean checkDuplicate)
      throws IOException {
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
    if (uri.getOrderNo() == null)
      uri.setOrderNo(uriMaxOrderNoPortOut.connect(uri.getParentId()) + 1);

    // og image 파일 업로드
    fileUploadPortIn
        .connect(FileUploadType.OG_IMAGE, ogImageFile)
        .ifPresent(
            postingFile ->
                uri.setOgImage(fileUrlPortIn.connect(ContentDispositionType.INLINE, postingFile)));

    return uriSavePortOut.connect(uri);
  }
}
