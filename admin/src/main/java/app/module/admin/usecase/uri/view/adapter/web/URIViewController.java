package app.module.admin.usecase.uri.view.adapter.web;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import app.module.core.config.adapter.web.rest.response.RestResponse;
import app.module.core.config.application.enums.AppType;
import app.module.core.usecase.uri.view.application.port.in.URIViewPortIn;
import app.module.core.usecase.uri.view.application.domain.URIView;
import app.module.core.config.application.util.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class URIViewController {

  private final MessageUtil messageUtil;
  private final URIViewPortIn uriViewPortIn;

  @GetMapping(path = "/api/uri/{appType}/{id}")
  ResponseEntity<?> operate(@PathVariable AppType appType, @PathVariable String id) {
    URIView output =
        uriViewPortIn
            .connect(appType, id)
            .orElseThrow(
                () ->
                    new NoSuchElementException(
                        messageUtil.getMessage("exception.noSuchElement", id, "URI")));

    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.builder()
                .item(
                    new URIBindingVO(
                        output.getId(),
                        output.getAppType(),
                        output.getText(),
                        output.getUri(),
                        output.getParentId(),
                        output.getOrderNo(),
                        output.getShowOnNavi(),
                        output.getOgTitle(),
                        output.getOgDesc(),
                        output.getOgKeywords(),
                        output.getOgImage(),
                        output.getRoles()))
                .build());
  }
}
