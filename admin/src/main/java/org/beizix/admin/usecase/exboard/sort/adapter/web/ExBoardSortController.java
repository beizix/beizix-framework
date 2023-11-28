package org.beizix.admin.usecase.exboard.sort.adapter.web;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.exboard.sort.application.domain.ExBoardSortCommand;
import org.beizix.admin.usecase.exboard.sort.application.port.in.ExBoardSortPortIn;
import org.beizix.core.config.adapter.web.rest.RestResponse;
import org.beizix.core.config.application.util.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class ExBoardSortController {
  private final ExBoardSortPortIn exBoardSortPortIn;
  private final MessageUtil messageUtil;

  @PostMapping("/api/exBoard/update/orderNo")
  ResponseEntity<?> operate(@RequestBody List<ExBoardSortVO> sortItems) {
    exBoardSortPortIn.operate(
        sortItems.stream()
            .map(item -> new ExBoardSortCommand(item.getId(), item.getOrderNo()))
            .collect(Collectors.toList()));
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.builder()
                .message(messageUtil.getMessage("operation.orderNo.updated"))
                .build());
  }
}
