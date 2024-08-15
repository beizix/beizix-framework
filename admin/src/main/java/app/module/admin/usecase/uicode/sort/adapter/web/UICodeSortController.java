package app.module.admin.usecase.uicode.sort.adapter.web;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.uicode.sort.application.port.in.UICodeSortCommand;
import app.module.admin.usecase.uicode.sort.application.port.in.UICodeSortPortIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class UICodeSortController {
  private final UICodeSortPortIn uiCodeSortPortIn;

  @PostMapping("/api/uicode/sort")
  ResponseEntity<?> process(@RequestBody List<UICodeSortVO> sortVOs) {
    uiCodeSortPortIn.connect(
        sortVOs.stream()
            .map(sortVO -> new UICodeSortCommand(sortVO.getId(), sortVO.getOrderNo()))
            .collect(Collectors.toList()));

    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
