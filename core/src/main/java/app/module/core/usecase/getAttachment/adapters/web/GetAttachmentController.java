package app.module.core.usecase.getAttachment.adapters.web;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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

  @GetMapping(path = "/content-disposition/attachment/{publicOrPrivate}/**")
  ResponseEntity<?> operate(
      @PathVariable String publicOrPrivate,
      @RequestParam(required = false) String originName,
      HttpServletRequest request)
      throws MalformedURLException {

    boolean isPrivate = "private".equals(publicOrPrivate);

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

    String subFilePath =
        request
            .getRequestURI()
            .replace("/content-disposition/attachment/" + publicOrPrivate + "/", "");

    String[] subFilePaths = subFilePath.split("/");

    String filePath = Paths.get(isPrivate ? privatePath : publicPath, subFilePaths).toString();

    UrlResource resource = new UrlResource("file:" + filePath);

    String fileName =
        StringUtils.isNotEmpty(originName)
            ? originName
            : subFilePaths[subFilePaths.length - 1];

    String encodedUploadFileName = UriUtils.encode(fileName, StandardCharsets.UTF_8);

    String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
        .body(resource);
  }
}
