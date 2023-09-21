package org.beizix.admin.feature.uicode.web;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.common.util.CoreUtil;
import org.beizix.core.config.exception.AlreadyExistItemException;
import org.beizix.core.feature.uicode.application.model.UICode;
import org.beizix.core.feature.uicode.application.service.UICodeCreateUpdateService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/uicode")
class UICodeCreateUpdateController {
  private final UICodeCreateUpdateService createService;
  private final ModelMapper modelMapper;
  private final CoreUtil coreUtil;

  @PostMapping("createUpdate")
  ResponseEntity<?> process(@Valid @RequestBody UICodeDto formDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }
    UICode item;

    try {
      item =
          createService.operate(
              modelMapper.map(formDto, UICode.class), "create".equals(formDto.getMode()));

    } catch (AlreadyExistItemException e) {
      bindingResult.rejectValue("id", "", e.getMessage());
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(item, UICodeDto.class));
  }
}
