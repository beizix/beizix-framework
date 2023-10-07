package org.beizix.admin.adapter.web.exboard;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.exboard.model.sort.ExBoardSortReqVO;
import org.beizix.core.application.domain.exboard.model.sort.ExBoardSortInput;
import org.beizix.core.application.port.in.exboard.ExBoardSortPortIn;
import org.beizix.core.common.rest.RestResponseDto;
import org.beizix.utility.common.MessageUtil;
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
  ResponseEntity<?> operate(@RequestBody List<ExBoardSortReqVO> sortItems) {
    exBoardSortPortIn.operate(
        sortItems.stream()
            .map(item -> new ExBoardSortInput(item.getId(), item.getOrderNo()))
            .collect(Collectors.toList()));
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            RestResponseDto.builder()
                .message(messageUtil.getMessage("operation.orderNo.updated"))
                .build());
  }
}
