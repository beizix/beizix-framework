package app.module.admin.config.adapter.persistence.initdata;

import app.module.core.config.application.enums.AppType;
import app.module.core.config.application.util.PropertyUtil;
import app.module.core.usecase.uri.save.application.domain.URISaveCommand;
import app.module.core.usecase.uri.save.application.port.in.URISavePortIn;
import java.io.IOException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(2)
public class InitUriAdminData implements ApplicationRunner {
  private final URISavePortIn uriSavePortIn;

  @Override
  public void run(ApplicationArguments args) throws IOException {
    if (!PropertyUtil.isCoreDataRequired()) return;

    final String ADMIN = "uri.admin";

    final String ADMIN_BOARD = "uri.admin.board";
    final String ADMIN_BOARD_EXAMPLE = "uri.admin.board.exampleBoard";
    final String ADMIN_BOARD_EXAMPLE_CREATE = "uri.admin.board.exampleBoard.create";
    final String ADMIN_BOARD_EXAMPLE_UPDATE = "uri.admin.board.exampleBoard.update.{{pathVar}}";
    final String ADMIN_BOARD_EXAMPLE_DELETE = "uri.admin.board.exampleBoard.delete";
    final String ADMIN_BOARD_EXAMPLE_EXCEL = "uri.admin.board.exampleBoard.excel";

    final String ADMIN_ARTICLE = "uri.admin.board.article";
    final String ADMIN_ARTICLE_CREATE = "uri.admin.board.article.create";
    final String ADMIN_ARTICLE_UPDATE = "uri.admin.board.article.update.{{pathVar}}";
    final String ADMIN_ARTICLE_DELETE = "uri.admin.board.article.delete";
    final String ADMIN_ARTICLE_EXCEL = "uri.admin.board.article.excel";

    final String ADMIN_SETTINGS = "uri.admin.settings";

    final String ADMIN_SETTINGS_ADMIN_GROUP = "uri.admin.settings.admins.group";
    final String ADMIN_SETTINGS_ADMIN = "uri.admin.settings.admins";
    final String ADMIN_SETTINGS_ADMIN_CREATE = "uri.admin.settings.admins.create";
    final String ADMIN_SETTINGS_ADMIN_UPDATE = "uri.admin.settings.admins.update.{{pathVar}}";
    final String ADMIN_SETTINGS_ADMIN_DELETE = "uri.admin.settings.admins.delete";
    final String ADMIN_SETTINGS_ADMIN_EXCEL = "uri.admin.settings.admins.excel";
    final String ADMIN_SETTINGS_ADMIN_ROLE = "uri.admin.settings.adminRoles";
    final String ADMIN_SETTINGS_ADMIN_PRIVILEGE = "uri.admin.settings.adminPrivilege";

    final String ADMIN_SETTINGS_USER_GROUP = "uri.admin.settings.user.group";
    final String ADMIN_SETTINGS_USER = "uri.admin.settings.user";
    final String ADMIN_SETTINGS_USER_UPDATE = "uri.admin.settings.user.update";
    final String ADMIN_SETTINGS_USER_ROLE = "uri.admin.settings.user.role";
    final String ADMIN_SETTINGS_USER_PRIVILEGE = "uri.admin.settings.user.privilege";

    final String ADMIN_SETTINGS_URI_ADMIN = "uri.admin.settings.uri.admin";
    final String ADMIN_SETTINGS_URI_FRONT = "uri.admin.settings.uri.front";
    final String ADMIN_SETTINGS_CODE = "uri.admin.settings.uicode";

    final String ADMIN_ANALYSIS = "uri.admin.analysis";
    final String ADMIN_ANALYSIS_OPERATION_LOG = "uri.admin.analysis.operationLog";
    final String ADMIN_ANALYSIS_OPERATION_LOG_UPDATE =
        "uri.admin.analysis.operationLog.{{pathVar}}";
    final String ADMIN_ANALYSIS_OPERATION_LOG_EXCEL = "uri.admin.analysis.operationLog.excel";

    final String ADMIN_ADD_ONS = "uri.admin.addons";
    final String ADMIN_ADD_ONS_SEND_EMAIL = "uri.admin.addons.email";
    final String ADMIN_ADD_ONS_SEND_EMAIL_CREATE = "uri.admin.addons.email.create";

    // 최상위 URI 정보
    appendURI(
        AppType.ADMIN,
        null,
        ADMIN,
        "메인",
        "/",
        true,
        Set.of("ROLE_SUPER", "ROLE_MANAGER", "ROLE_STAFF"));

    // `1` depth URI 정의 - 시작
    appendURI(AppType.ADMIN, ADMIN, ADMIN_BOARD, "게시판", "/board", true, Set.of("ROLE_SUPER"));

    appendURI(
        AppType.ADMIN,
        ADMIN,
        ADMIN_SETTINGS,
        "Settings",
        "/true",
        true,
        Set.of("ROLE_SUPER", "ROLE_MANAGER"));

    appendURI(AppType.ADMIN, ADMIN, ADMIN_ANALYSIS, "분석", "/analysis", true, Set.of("ROLE_SUPER"));

    appendURI(
        AppType.ADMIN,
        ADMIN,
        ADMIN_ADD_ONS,
        "Add-ons (부가기능)",
        "/add-ons",
        true,
        Set.of("ROLE_SUPER"));
    // `1` depth URI 정의 - 끝

    appendURI(
        AppType.ADMIN,
        ADMIN_SETTINGS,
        ADMIN_SETTINGS_ADMIN_GROUP,
        "관리자 & 역할",
        "/settings/adminGroup",
        true,
        Set.of("ROLE_SUPER", "ROLE_MANAGER"));

    appendURI(
        AppType.ADMIN,
        ADMIN_SETTINGS_ADMIN_GROUP,
        ADMIN_SETTINGS_ADMIN,
        "관리자 목록",
        "/settings/admins",
        true,
        Set.of("ROLE_SUPER", "ROLE_MANAGER"));

    appendURI(
        AppType.ADMIN,
        ADMIN_SETTINGS_ADMIN,
        ADMIN_SETTINGS_ADMIN_CREATE,
        "관리자 등록",
        "/settings/admins/create",
        false,
        Set.of("ROLE_SUPER", "ROLE_MANAGER"));

    appendURI(
        AppType.ADMIN,
        ADMIN_SETTINGS_ADMIN,
        ADMIN_SETTINGS_ADMIN_UPDATE,
        "관리자 수정",
        "/settings/admins/update/{{pathVar}}",
        false,
        Set.of("ROLE_SUPER", "ROLE_MANAGER"));

    appendURI(
        AppType.ADMIN,
        ADMIN_SETTINGS_ADMIN,
        ADMIN_SETTINGS_ADMIN_EXCEL,
        "관리자 엑셀 다운로드",
        "/settings/admins/excel",
        false,
        Set.of("ROLE_SUPER", "ROLE_MANAGER"));

    appendURI(
        AppType.ADMIN,
        ADMIN_SETTINGS_ADMIN_GROUP,
        ADMIN_SETTINGS_ADMIN_ROLE,
        "관리자 역할",
        "/settings/adminRoles",
        true,
        Set.of("ROLE_SUPER", "ROLE_MANAGER"));

    appendURI(
        AppType.ADMIN,
        ADMIN_SETTINGS_ADMIN_GROUP,
        ADMIN_SETTINGS_ADMIN_PRIVILEGE,
        "관리자 권한",
        "/settings/adminPrivilege",
        true,
        Set.of("ROLE_SUPER", "ROLE_MANAGER"));

    // 사용자 & 역할 - 시작
    appendURI(
        AppType.ADMIN,
        ADMIN_SETTINGS,
        ADMIN_SETTINGS_USER_GROUP,
        "사용자 & 역할",
        "/settings/userGroup",
        true,
        Set.of("ROLE_SUPER", "ROLE_MANAGER"));

    appendURI(
        AppType.ADMIN,
        ADMIN_SETTINGS_USER_GROUP,
        ADMIN_SETTINGS_USER,
        "사용자 목록",
        "/settings/users",
        true,
        Set.of("ROLE_SUPER", "ROLE_MANAGER"));

    appendURI(
        AppType.ADMIN,
        ADMIN_SETTINGS_USER,
        ADMIN_SETTINGS_USER_UPDATE,
        "사용자 수정",
        "/settings/users/update/{{pathVar}}",
        false,
        Set.of("ROLE_SUPER", "ROLE_MANAGER"));

    appendURI(
        AppType.ADMIN,
        ADMIN_SETTINGS_USER_GROUP,
        ADMIN_SETTINGS_USER_ROLE,
        "사용자 역할",
        "/settings/userRoles",
        true,
        Set.of("ROLE_SUPER", "ROLE_MANAGER"));

    appendURI(
        AppType.ADMIN,
        ADMIN_SETTINGS_USER_GROUP,
        ADMIN_SETTINGS_USER_PRIVILEGE,
        "사용자 권한",
        "/settings/userPrivilege",
        true,
        Set.of("ROLE_SUPER", "ROLE_MANAGER"));
    // 사용자 & 역할 - 끝

    appendURI(
        AppType.ADMIN,
        ADMIN_SETTINGS,
        ADMIN_SETTINGS_URI_ADMIN,
        "URI 관리 (관리자)",
        "/settings/uri/ADMIN",
        true,
        Set.of("ROLE_SUPER", "ROLE_MANAGER"));

    appendURI(
        AppType.ADMIN,
        ADMIN_SETTINGS,
        ADMIN_SETTINGS_URI_FRONT,
        "URI 관리 (FRONT)",
        "/settings/uri/FRONT",
        true,
        Set.of("ROLE_SUPER", "ROLE_MANAGER"));

    appendURI(
        AppType.ADMIN,
        ADMIN_SETTINGS,
        ADMIN_SETTINGS_CODE,
        "UI 코드 (공통코드)",
        "/settings/uicode",
        true,
        Set.of("ROLE_SUPER", "ROLE_MANAGER"));

    // 예제 게시판 - 시작
    //
    appendURI(
        AppType.ADMIN,
        ADMIN_BOARD,
        ADMIN_ARTICLE,
        "예제 게시판",
        "/board/articles",
        true,
        Set.of("ROLE_SUPER", "ROLE_MANAGER", "ROLE_STAFF"));

    appendURI(
        AppType.ADMIN,
        ADMIN_ARTICLE,
        ADMIN_ARTICLE_CREATE,
        "예제 게시판 등록",
        "/board/articles/create",
        false,
        Set.of("ROLE_SUPER", "ROLE_MANAGER", "ROLE_STAFF"));

    appendURI(
        AppType.ADMIN,
        ADMIN_ARTICLE,
        ADMIN_ARTICLE_UPDATE,
        "예제 게시판 수정",
        "/board/articles/{{pathVar}}",
        false,
        Set.of("ROLE_SUPER", "ROLE_MANAGER", "ROLE_STAFF"));

    appendURI(
        AppType.ADMIN,
        ADMIN_ARTICLE,
        ADMIN_ARTICLE_DELETE,
        "예제 게시판 삭제",
        "/board/articles/delete",
        false,
        Set.of("ROLE_SUPER", "ROLE_MANAGER", "ROLE_STAFF"));

    appendURI(
        AppType.ADMIN,
        ADMIN_ARTICLE,
        ADMIN_ARTICLE_EXCEL,
        "예제 게시판 엑셀 다운로드",
        "/board/articles/excel",
        false,
        Set.of("ROLE_SUPER", "ROLE_MANAGER", "ROLE_STAFF"));
    //
    appendURI(
        AppType.ADMIN,
        ADMIN_BOARD,
        ADMIN_BOARD_EXAMPLE,
        "예제 게시판",
        "/board/exampleBoard",
        true,
        Set.of("ROLE_SUPER", "ROLE_MANAGER", "ROLE_STAFF"));

    appendURI(
        AppType.ADMIN,
        ADMIN_BOARD,
        ADMIN_BOARD_EXAMPLE_CREATE,
        "예제 게시판 등록",
        "/board/exampleBoard/create",
        false,
        Set.of("ROLE_SUPER", "ROLE_MANAGER", "ROLE_STAFF"));

    appendURI(
        AppType.ADMIN,
        ADMIN_BOARD,
        ADMIN_BOARD_EXAMPLE_UPDATE,
        "예제 게시판 수정",
        "/board/exampleBoard/update/{{pathVar}}",
        false,
        Set.of("ROLE_SUPER", "ROLE_MANAGER", "ROLE_STAFF"));

    appendURI(
        AppType.ADMIN,
        ADMIN_BOARD,
        ADMIN_BOARD_EXAMPLE_DELETE,
        "예제 게시판 삭제",
        "/board/exampleBoard/delete",
        false,
        Set.of("ROLE_SUPER", "ROLE_MANAGER", "ROLE_STAFF"));

    appendURI(
        AppType.ADMIN,
        ADMIN_BOARD_EXAMPLE,
        ADMIN_BOARD_EXAMPLE_EXCEL,
        "예제 게시판 엑셀 다운로드",
        "/board/exampleBoard/excel",
        false,
        Set.of("ROLE_SUPER", "ROLE_MANAGER", "ROLE_STAFF"));
    // 예제 게시판 - 끝

    appendURI(
        AppType.ADMIN,
        ADMIN_ANALYSIS,
        ADMIN_ANALYSIS_OPERATION_LOG,
        "Operation Log (수행로그)",
        "/analysis/operationlog",
        true,
        Set.of("ROLE_SUPER"));

    appendURI(
        AppType.ADMIN,
        ADMIN_ANALYSIS,
        ADMIN_ANALYSIS_OPERATION_LOG_UPDATE,
        "수행로그 상세",
        "/analysis/operationlog/{{pathVar}}",
        false,
        Set.of("ROLE_SUPER"));

    appendURI(
        AppType.ADMIN,
        ADMIN_ANALYSIS_OPERATION_LOG,
        ADMIN_ANALYSIS_OPERATION_LOG_EXCEL,
        "수행로그 Excel",
        "/analysis/operationlog/excel",
        false,
        Set.of("ROLE_SUPER"));

    appendURI(
        AppType.ADMIN,
        ADMIN_ADD_ONS,
        ADMIN_ADD_ONS_SEND_EMAIL,
        "Email 발송",
        "/addons/email/form",
        true,
        Set.of("ROLE_SUPER"));

    appendURI(
        AppType.ADMIN,
        ADMIN_ADD_ONS_SEND_EMAIL,
        ADMIN_ADD_ONS_SEND_EMAIL_CREATE,
        "Email 입력폼",
        "/addons/email/form",
        false,
        Set.of("ROLE_SUPER"));
  }

  private void appendURI(
      AppType appType,
      String parentId,
      String menuId,
      String menuTitle,
      String uri,
      boolean showOnNavi,
      Set<String> roles)
      throws IOException {
    uriSavePortIn.connect(
        new URISaveCommand(
            menuId,
            parentId,
            appType,
            uri,
            showOnNavi,
            menuTitle,
            null,
            menuTitle,
            menuTitle,
            null,
            null,
            roles),
        null,
        false);
  }
}
