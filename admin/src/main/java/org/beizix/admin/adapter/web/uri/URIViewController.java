package org.beizix.admin.adapter.web.uri;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.application.domain.uri.model.URIInput;
import org.beizix.core.application.port.in.uri.URIViewPortIn;
import org.beizix.utility.common.MessageUtil;
import org.beizix.core.common.rest.RestResponse;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class URIViewController {
  private final MessageUtil messageUtil;
  private final ModelMapper modelMapper;
  private final URIViewPortIn uriViewPortIn;

  @GetMapping(path = "/api/uri/get/{appType}/{id}")
  ResponseEntity<?> operate(@PathVariable AppType appType, @PathVariable String id) {
    URIInput item =
        uriViewPortIn
            .connect(appType, id)
            .orElseThrow(
                () ->
                    new NoSuchElementException(
                        messageUtil.getMessage("exception.noSuchElement", id, "URI")));
    return ResponseEntity.status(HttpStatus.OK)
        .body(RestResponse.builder().item(modelMapper.map(item, URIDto.class)).build());
  }
}
