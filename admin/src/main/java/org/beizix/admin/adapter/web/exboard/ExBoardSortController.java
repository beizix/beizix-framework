package org.beizix.admin.adapter.web.exboard;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.admin.adapter.web.exboard.model.ExBoardDto;
import org.beizix.core.common.rest.RestResponseDto;
import org.beizix.utility.common.MessageUtil;
import org.beizix.core.application.domain.exboard.model.ExBoardInput;
import org.beizix.core.application.port.in.exboard.ExBoardSortPortIn;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
class ExBoardSortController {
  private final ExBoardSortPortIn exBoardSortPortIn;
  private final ModelMapper modelMapper;
  private final MessageUtil messageUtil;

  @PostMapping("/api/exBoard/update/orderNo")
  ResponseEntity<?> operate(@RequestBody List<ExBoardDto> sortItems) {
    exBoardSortPortIn.operate(
        sortItems.stream()
            .map(item -> modelMapper.map(item, ExBoardInput.class))
            .collect(Collectors.toList()));
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponseDto.builder()
                .message(messageUtil.getMessage("operation.orderNo.updated"))
                .build());
  }
}
