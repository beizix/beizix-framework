package org.beizix.admin.usecase.uicode.view.adapter.web;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.uicode.view.application.port.in.UICodeViewPortIn;
import org.beizix.admin.usecase.uicode.view.domain.UICodeView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class UICodeViewController {
  private final UICodeViewPortIn uiCodeViewPortIn;

  @GetMapping("/api/uicode/{id}")
  ResponseEntity<?> process(@PathVariable String id) {
    UICodeView item =
        uiCodeViewPortIn
            .connect(id)
            .orElseThrow(
                () ->
                    new NoSuchElementException(
                        String.format("'%s' - 존재하지 않는 UI Code 아이디 입니다.", id)));

    return ResponseEntity.status(HttpStatus.OK).body(item);
  }
}
