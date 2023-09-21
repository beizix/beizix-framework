package org.beizix.admin.feature.uicode.web;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.feature.uicode.application.service.UICodeViewService;
import org.beizix.core.feature.uicode.application.model.UICode;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/uicode")
class UICodeViewController {
  private final UICodeViewService uiCodeViewService;
  private final ModelMapper modelMapper;

  @GetMapping("get/{id}")
  ResponseEntity<?> process(@PathVariable String id) {
    UICode item =
        uiCodeViewService
            .operate(id)
            .orElseThrow(
                () ->
                    new NoSuchElementException(
                        String.format("'%s' - 존재하지 않는 UI Code 아이디 입니다.", id)));

    return ResponseEntity.status(HttpStatus.OK)
        .body(modelMapper.map(item, UICodeDto.class));
  }
}
