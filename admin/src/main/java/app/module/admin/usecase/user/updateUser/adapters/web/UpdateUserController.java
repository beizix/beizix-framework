package app.module.admin.usecase.user.updateUser.adapters.web;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import app.module.admin.usecase.user.updateUser.adapters.web.model.UpdateUserReqVO;

@RestController
@RequiredArgsConstructor
class UpdateUserController {
  @PutMapping(path = "/api/settings/users/update")
  ResponseEntity<?> operate(@Valid UpdateUserReqVO reqVO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(bindingResult.getFieldErrors());
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(reqVO);
  }
}