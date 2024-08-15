package app.module.admin.usecase.uicode.remove.adapter.web;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.uicode.save.adapter.web.UICodeSaveVO;
import app.module.admin.usecase.uicode.remove.application.port.in.UICodeRemovePortIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class UICodeRemoveController {

  private final UICodeRemovePortIn uiCodeRemovePortIn;

  @PostMapping("/api/uicode/remove")
  ResponseEntity<?> process(UICodeSaveVO formDto) {
    uiCodeRemovePortIn.operate(formDto.getId());
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
