package app.module.admin.usecase.user.updateUser.adapters.web;

import app.module.admin.usecase.user.updateUser.adapters.web.model.UpdateUserReqVO;
import app.module.admin.usecase.user.updateUser.ports.UpdateUserPortIn;
import app.module.admin.usecase.user.updateUser.ports.application.domain.UpdateUserCmd;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class UpdateUserController {
  private final UpdateUserPortIn updateUserPortIn;

  @PatchMapping(path = "/api/settings/users/update")
  ResponseEntity<?> operate(@Valid UpdateUserReqVO reqVO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
          .body(bindingResult.getFieldErrors());
    }

    updateUserPortIn.operate(
        new UpdateUserCmd(
            reqVO.getId(),
            reqVO.getEmail(),
            reqVO.getAccountDisabled(),
            reqVO.getLoginFailCnt(),
            reqVO.getAccountLocked(),
            reqVO.getRoles()));

    return ResponseEntity.status(HttpStatus.OK).body(reqVO);
  }
}
