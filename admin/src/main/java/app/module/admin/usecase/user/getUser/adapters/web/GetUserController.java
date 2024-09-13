package app.module.admin.usecase.user.getUser.adapters.web;

import app.module.core.usecase.user.findUser.ports.FindUserPortIn;
import app.module.core.usecase.user.findUser.ports.application.domain.FindUser;
import app.module.core.usecase.user.findUser.ports.application.domain.FindUserCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class GetUserController {
  private final FindUserPortIn findUserPortIn;

  @GetMapping(path = "/api/settings/users/{id}")
  ResponseEntity<?> operate(@PathVariable String id) {
    FindUser findUser = findUserPortIn.operate(new FindUserCmd(id)).orElseThrow();

    // 불필요한 정보는 제거
    findUser.setPassword(null);

    return ResponseEntity.status(HttpStatus.OK).body(findUser);
  }
}
