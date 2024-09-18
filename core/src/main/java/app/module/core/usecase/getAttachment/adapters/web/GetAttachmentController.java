package app.module.core.usecase.getAttachment.adapters.web;

import app.module.core.usecase.file.getFile.ports.GetFilePortIn;
import app.module.core.usecase.file.getFile.ports.application.domain.GetFile;
import app.module.core.usecase.file.getFile.ports.application.domain.GetFileCmd;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

@RestController
@RequiredArgsConstructor
class GetAttachmentController {
  @Value("${path.upload.public}")
  private String publicPath;

  @Value("${path.upload.private}")
  private String privatePath;

  @Value("${app.aws.cloudfront.domain:null}")
  private String cloudFrontDomain;

  @Value("${app.aws.s3.bucketFolder:null}")
  private String bucketFolder;

  private final GetFilePortIn getFilePortIn;

  @GetMapping(path = "/content-disposition/attachment/{fileId}")
  ResponseEntity<?> operate(@PathVariable Long fileId) throws MalformedURLException {

    GetFile getFile =
        getFilePortIn
            .operate(new GetFileCmd(fileId))
            .orElseThrow(
                () -> new RuntimeException(String.format("파일을 찾을 수 없습니다. ID: %s", fileId)));

    boolean isPrivate = !getFile.getType().isPubic();

    if (isPrivate) {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      auth.getAuthorities().stream()
          .filter(
              grantedAuthority ->
                  grantedAuthority.getAuthority().equals("ROLE_SUPER")
                      || grantedAuthority.getAuthority().equals("PRIVATE_DOWNLOAD"))
          .findFirst()
          .orElseThrow(
              () -> new AccessDeniedException("[AccessDenied] Private 파일 리소스 접근 권한이 없습니다."));
    }

    UrlResource resource = null;

    switch (getFile.getType().getFileStorageType()) {
      case LOCAL:
        String filePath =
            Paths.get(isPrivate ? privatePath : publicPath, getFile.getPath(), getFile.getName())
                .toString();

        resource = new UrlResource("file:" + filePath);
        break;
      case S3:
        resource =
            new UrlResource(
                "https://"
                    + cloudFrontDomain
                    + bucketFolder
                    + getFile.getPath()
                    + "/"
                    + getFile.getName());
        break;
    }

    String encodedUploadFileName = UriUtils.encode(getFile.getOriginName(), StandardCharsets.UTF_8);

    String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
        .body(resource);
  }
}
