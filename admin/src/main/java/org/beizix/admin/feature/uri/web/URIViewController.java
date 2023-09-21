package org.beizix.admin.feature.uri.web;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.uri.application.model.URI;
import org.beizix.core.feature.uri.application.service.URIViewService;
import org.beizix.utility.common.MessageUtil;
import org.beizix.core.common.rest.RestResponseDto;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class URIViewController {
  private final MessageUtil messageUtil;
  private final ModelMapper modelMapper;
  private final URIViewService uriViewService;

  @GetMapping(path = "/api/uri/get/{appType}/{id}")
  ResponseEntity<?> operate(@PathVariable AppType appType, @PathVariable String id) {
    URI item =
        uriViewService
            .operate(appType, id)
            .orElseThrow(
                () ->
                    new NoSuchElementException(
                        messageUtil.getMessage("exception.noSuchElement", id, "URI")));
    return ResponseEntity.status(HttpStatus.OK)
        .body(RestResponseDto.builder().item(modelMapper.map(item, URIDto.class)).build());
  }
}
