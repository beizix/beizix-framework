package app.module.admin.usecase.uicode.toptier.adapter.web;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.uicode.toptier.application.port.in.UICodeTopTierPortIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class UICodeTopTierController {
  private final UICodeTopTierPortIn topTierPortIn;

  @GetMapping("/api/uicode/topTier")
  ResponseEntity<?> process() {
    return ResponseEntity.status(HttpStatus.OK).body(topTierPortIn.connect());
  }
}
