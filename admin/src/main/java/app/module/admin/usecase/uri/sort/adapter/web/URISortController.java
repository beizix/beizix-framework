package app.module.admin.usecase.uri.sort.adapter.web;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import app.module.admin.usecase.uri.sort.application.domain.URISortCommand;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.uri.sort.application.port.in.URISortPortIn;
import app.module.core.config.application.util.CoreUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class URISortController {
  private final URISortPortIn uriSortPortIn;
  private final CoreUtil coreUtil;

  @PostMapping(path = "/api/uri/switchOrderNo")
  ResponseEntity<?> switchOrderNo(
      @Valid @RequestBody List<URISortVO> sortVOs, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return coreUtil.getValidationFailResponseEntity(bindingResult);
    }

    uriSortPortIn.connect(
        sortVOs.stream()
            .map(item -> new URISortCommand(item.getId(), item.getOrderNo()))
            .collect(Collectors.toList()));

    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
