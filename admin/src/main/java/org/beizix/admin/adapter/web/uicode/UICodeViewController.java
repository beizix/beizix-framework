package org.beizix.admin.adapter.web.uicode;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.uicode.model.UICodeDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.beizix.core.application.port.in.uicode.UICodeViewPortIn;
import org.beizix.core.application.domain.uicode.model.UICodeInput;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/uicode")
class UICodeViewController {
  private final UICodeViewPortIn uiCodeViewPortIn;
  private final ModelMapper modelMapper;

  @GetMapping("get/{id}")
  ResponseEntity<?> process(@PathVariable String id) {
    UICodeInput item =
        uiCodeViewPortIn
            .connect(id)
            .orElseThrow(
                () ->
                    new NoSuchElementException(
                        String.format("'%s' - 존재하지 않는 UI Code 아이디 입니다.", id)));

    return ResponseEntity.status(HttpStatus.OK)
        .body(modelMapper.map(item, UICodeDto.class));
  }
}
