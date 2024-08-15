package app.module.admin.usecase.uicode.save.adapter.web;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.uicode.save.application.port.in.UICodeSaveCommand;
import app.module.admin.usecase.uicode.save.application.port.in.UICodeSavePortIn;
import app.module.core.config.application.util.CoreUtil;
import app.module.core.config.application.exception.AlreadyExistItemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class UICodeSaveController {
  private final UICodeSavePortIn createService;
  private final CoreUtil coreUtil;

  @PostMapping("/api/uicode/save")
  ResponseEntity<?> process(@Valid @RequestBody UICodeSaveVO saveVO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }
    String savedId;

    try {
      savedId =
          createService.connect(
              new UICodeSaveCommand(
                  saveVO.getId(),
                  saveVO.getParentId(),
                  saveVO.getOrderNo(),
                  saveVO.getCodeText(),
                  saveVO.getCodeValue(),
                  saveVO.getMsgCode(),
                  saveVO.getInUse()),
              "create".equals(saveVO.getMode()));

    } catch (AlreadyExistItemException e) {
      bindingResult.rejectValue("id", "", e.getMessage());
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    return ResponseEntity.status(HttpStatus.OK).body(savedId);
  }
}
