package org.beizix.admin.feature.uri.web;

import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.common.rest.RestResponse;
import org.beizix.core.common.util.CoreUtil;
import org.beizix.core.application.domain.uri.model.URISortInput;
import org.beizix.core.application.port.in.uri.URISortPortIn;
import org.beizix.utility.common.MessageUtil;

@RestController
@RequiredArgsConstructor
public class URISortController {
  private final URISortPortIn uriSortPortIn;
  private final CoreUtil coreUtil;
  private final MessageUtil messageUtil;
  private final ModelMapper modelMapper;

  @PostMapping(path = "/api/uri/switchOrderNo")
  ResponseEntity<?> switchOrderNo(
      @Valid @RequestBody URISortDto formDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    uriSortPortIn.connect(
        formDto.getUpdateList().stream()
            .map(item -> modelMapper.map(item, URISortInput.class))
            .collect(Collectors.toList()));

    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.builder()
                .item(URIDto.builder().id(formDto.getId()).build())
                .message(messageUtil.getMessage("operation.common.move.done"))
                .build());
  }
}
