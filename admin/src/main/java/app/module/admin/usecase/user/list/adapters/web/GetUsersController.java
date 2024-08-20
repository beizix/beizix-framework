package app.module.admin.usecase.user.list.adapters.web;

import app.module.admin.usecase.user.list.adapters.web.model.GetUsersReqVO;
import app.module.admin.usecase.user.list.ports.GetUserListPortIn;
import app.module.admin.usecase.user.list.ports.application.domain.GetUsers;
import app.module.admin.usecase.user.list.ports.application.domain.GetUsersCmd;
import app.module.core.config.adapter.persistence.entity.FrontUser_;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
class GetUsersController {
  private final GetUserListPortIn getUserListPortIn;

  @GetMapping(path = "/settings/users")
  String operate(
      @PageableDefault(sort = FrontUser_.CREATED_AT) Pageable pageable,
      Model model,
      @ModelAttribute GetUsersReqVO reqVO) {

    Page<GetUsers> result =
        getUserListPortIn.operate(
            pageable,
            new GetUsersCmd(reqVO.getSearchField(), reqVO.getSearchField(), reqVO.getSearchRole()));

    model.addAttribute("output", result);

    return "settings/users";
  }
}
