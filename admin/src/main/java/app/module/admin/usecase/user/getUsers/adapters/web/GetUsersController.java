package app.module.admin.usecase.user.getUsers.adapters.web;

import app.module.admin.usecase.user.getUsers.adapters.web.model.GetUsersReqVO;
import app.module.admin.usecase.user.getUsers.ports.GetUsersPortIn;
import app.module.admin.usecase.user.getUsers.ports.application.domain.GetUsers;
import app.module.admin.usecase.user.getUsers.ports.application.domain.GetUsersCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class GetUsersController {
  private final GetUsersPortIn getUsersPortIn;

  @GetMapping(path = "/api/settings/users")
  ResponseEntity<?> operate(@PageableDefault Pageable pageable, GetUsersReqVO reqVO) {
    Page<GetUsers> users =
        getUsersPortIn.operate(
            pageable,
            new GetUsersCmd(reqVO.getSearchField(), reqVO.getSearchValue(), reqVO.getSearchRole()));

    return ResponseEntity.status(HttpStatus.OK).body(users);
  }
}
