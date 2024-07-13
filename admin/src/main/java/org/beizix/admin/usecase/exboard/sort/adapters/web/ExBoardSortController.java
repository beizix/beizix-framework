package org.beizix.admin.usecase.exboard.sort.adapters.web;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.exboard.sort.adapters.web.model.ExBoardSortVO;
import org.beizix.admin.usecase.exboard.sort.ports.application.domain.ExBoardSortCmd;
import org.beizix.admin.usecase.exboard.sort.ports.ExBoardSortPortIn;
import org.beizix.core.config.adapter.web.rest.response.RestResponse;
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
            .map(item -> new ExBoardSortCmd(item.getId(), item.getOrderNo()))
            .collect(Collectors.toList()));
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponse.builder()
                .message(messageUtil.getMessage("operation.orderNo.updated"))
                .build());
  }
}
