package app.module.core.usecase.contentdisposition.attach.adapter.web;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriUtils;

//@Controller
@RequiredArgsConstructor
public class ContentDispositionAttachController {
  @Value("${path.upload.public}")
  private String publicPath;

  @Value("${path.upload.private}")
  private String privatePath;

  @GetMapping(value = "/content-disposition/attachment/{disclosure}")
  ResponseEntity<Resource> operate(@PathVariable String disclosure, SaveToStorage saveToStorage)
      throws MalformedURLException {
    boolean isPrivate = "private".equals(disclosure);

    if (isPrivate) {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      auth.getAuthorities().stream()
          .filter(
              grantedAuthority ->
                  grantedAuthority.getAuthority().equals("ROLE_SUPER")
                      || grantedAuthority.getAuthority().equals("PRIVATE_DOWNLOAD_PRIVILEGE"))
          .findFirst()
          .orElseThrow(
              () -> new AccessDeniedException("[AccessDenied] Private 파일 리소스 접근 권한이 없습니다."));
    }

    String filePath =
        Paths.get(
                isPrivate ? privatePath : publicPath,
                saveToStorage.getPath(),
                saveToStorage.getName())
            .toString();

    UrlResource resource = new UrlResource("file:" + filePath);

    String encodedUploadFileName =
        UriUtils.encode(saveToStorage.getOriginName(), StandardCharsets.UTF_8);
    String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
        .body(resource);
  }
}
