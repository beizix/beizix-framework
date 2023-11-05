package org.beizix.admin.adapter.web.uri;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.uri.model.view.URIBindingVO;
import org.beizix.core.application.domain.uri.model.list.URIOutput;
import org.beizix.core.application.port.in.uri.URIViewPortIn;
import org.beizix.core.common.rest.RestResponse;
import org.beizix.core.config.enums.AppType;
import org.beizix.utility.common.MessageUtil;
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

  @GetMapping(path = "/api/uri/get/{appType}/{id}")
  ResponseEntity<?> operate(@PathVariable AppType appType, @PathVariable String id) {
    URIOutput output =
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