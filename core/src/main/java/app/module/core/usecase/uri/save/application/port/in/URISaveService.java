package app.module.core.usecase.uri.save.application.port.in;

import app.module.core.config.application.enums.FileUploadType;
import app.module.core.config.application.exception.AlreadyExistItemException;
import app.module.core.config.application.util.MessageUtil;
import app.module.core.usecase.file.saveToStorage.ports.SaveToStoragePortIn;
import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;
import app.module.core.usecase.file.url.application.port.in.FileUrlPortIn;
import app.module.core.usecase.uri.save.application.domain.URISaveCommand;
import app.module.core.usecase.uri.save.application.port.out.URIMaxOrderNoPortOut;
import app.module.core.usecase.uri.save.application.port.out.URISavePortOut;
import app.module.core.usecase.uri.view.application.port.in.URIViewPortIn;
import java.io.IOException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
  private final SaveToStoragePortIn saveToStoragePortIn;
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

    if (ogImageFile != null && !ogImageFile.isEmpty()) {
      // og image 파일 업로드
      SaveToStorage ogImg =
          saveToStoragePortIn.operate(FileUploadType.OG_IMAGE, ogImageFile).orElseThrow();

      uri.setOgImage(
          fileUrlPortIn.getInline(
              ogImg.getType().getFileStorageType(), ogImg.getPath(), ogImg.getName()));
    }

    return uriSavePortOut.connect(uri);
  }
}
