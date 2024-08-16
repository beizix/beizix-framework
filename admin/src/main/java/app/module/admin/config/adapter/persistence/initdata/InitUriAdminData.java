package app.module.admin.config.adapter.persistence.initdata;

import java.io.IOException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import app.module.core.usecase.uri.save.application.port.in.URISavePortIn;
import app.module.core.usecase.uri.save.application.domain.URISaveCommand;
import app.module.core.config.application.enums.AppType;
import app.module.core.config.application.util.PropertyUtil;
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

    final String ADMIN_SETTINGS = "uri.admin.settings";

    final String ADMIN_SETTINGS_ADMIN_GROUP = "uri.admin.settings.admins.group";
    final String ADMIN_SETTINGS_ADMIN = "uri.admin.settings.admins";
    final String ADMIN_SETTINGS_ADMIN_CREATE = "uri.admin.settings.admins.create";
    final String ADMIN_SETTINGS_ADMIN_UPDATE = "uri.admin.settings.admins.update.{{pathVar}}";
    final String ADMIN_SETTINGS_ADMIN_DELETE = "uri.admin.settings.admins.delete";
    final String ADMIN_SETTINGS_ADMIN_EXCEL = "uri.admin.settings.admins.excel";
    final String ADMIN_SETTINGS_ADMIN_ROLE = "uri.admin.settings.adminRoles";
    final String ADMIN_SETTINGS_ADMIN_PRIVILEGE = "uri.admin.settings.adminPrivilege";

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

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN,
            null,
            AppType.ADMIN,
            "/",
            true,
            "관리자",
            null,
            "어드민 홈",
            "어드민 홈 페이지",
            "admin",
            null,
            Set.of("ROLE_SUPER", "ROLE_MANAGER", "ROLE_STAFF")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_BOARD,
            ADMIN,
            AppType.ADMIN,
            "/board",
            true,
            "게시판",
            null,
            null,
            null,
            null,
            null,
            Set.of("ROLE_SUPER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_SETTINGS,
            ADMIN,
            AppType.ADMIN,
            "/settings",
            true,
            "Settings",
            null,
            null,
            null,
            null,
            null,
            Set.of("ROLE_SUPER", "ROLE_MANAGER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_SETTINGS_ADMIN_GROUP,
            ADMIN_SETTINGS,
            AppType.ADMIN,
            "/settings/adminGroup",
            true,
            "관리자 & 역할",
            null,
            "관리자",
            "관리자 목록 화면",
            "notice,list",
            null,
            Set.of("ROLE_SUPER", "ROLE_MANAGER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_SETTINGS_ADMIN,
            ADMIN_SETTINGS_ADMIN_GROUP,
            AppType.ADMIN,
            "/settings/admins",
            true,
            "관리자",
            null,
            "관리자",
            "관리자 목록 화면",
            "notice,list",
            null,
            Set.of("ROLE_SUPER", "ROLE_MANAGER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_SETTINGS_ADMIN_CREATE,
            ADMIN_SETTINGS_ADMIN,
            AppType.ADMIN,
            "/settings/admins/create",
            false,
            "등록",
            null,
            "관리자",
            "관리자 등록 화면",
            "notice,create",
            null,
            Set.of("ROLE_SUPER", "ROLE_MANAGER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_SETTINGS_ADMIN_UPDATE,
            ADMIN_SETTINGS_ADMIN,
            AppType.ADMIN,
            "/settings/admins/update/{{pathVar}}",
            false,
            "수정",
            null,
            "관리자",
            "관리자 수정 화면",
            "notice,update",
            null,
            Set.of("ROLE_SUPER", "ROLE_MANAGER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_SETTINGS_ADMIN_ROLE,
            ADMIN_SETTINGS_ADMIN_GROUP,
            AppType.ADMIN,
            "/settings/adminRoles",
            true,
            "관리자 역할",
            null,
            "관리자 역할",
            "관리자 역할 목록 화면",
            "role,authorities",
            null,
            Set.of("ROLE_SUPER", "ROLE_MANAGER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_SETTINGS_ADMIN_PRIVILEGE,
            ADMIN_SETTINGS_ADMIN_GROUP,
            AppType.ADMIN,
            "/settings/adminPrivilege",
            true,
            "관리자 권한",
            null,
            "관리자 권한",
            "관리자 권한 목록 화면",
            "role,authorities",
            null,
            Set.of("ROLE_SUPER", "ROLE_MANAGER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_SETTINGS_URI_ADMIN,
            ADMIN_SETTINGS,
            AppType.ADMIN,
            "/settings/uri/ADMIN",
            true,
            "URI 관리 (관리자)",
            null,
            "URI",
            "URI 관리 화면",
            "uri",
            null,
            Set.of("ROLE_SUPER", "ROLE_MANAGER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_BOARD_EXAMPLE,
            ADMIN_BOARD,
            AppType.ADMIN,
            "/board/exampleBoard",
            true,
            "예제 게시판",
            null,
            "예제 게시판",
            "예제 게시판 관리 페이지",
            "notice",
            null,
            Set.of("ROLE_SUPER", "ROLE_MANAGER", "ROLE_STAFF")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_BOARD_EXAMPLE_CREATE,
            ADMIN_BOARD_EXAMPLE,
            AppType.ADMIN,
            "/board/exampleBoard/create",
            false,
            "등록",
            null,
            "예제 게시판",
            "예제 게시판 등록 페이지",
            "notice,create",
            null,
            Set.of("ROLE_SUPER", "ROLE_MANAGER", "ROLE_STAFF")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_BOARD_EXAMPLE_UPDATE,
            ADMIN_BOARD_EXAMPLE,
            AppType.ADMIN,
            "/board/exampleBoard/update/{{pathVar}}",
            false,
            "수정",
            null,
            "예제 게시판",
            "예제 게시판 수정 페이지",
            "notice,update",
            null,
            Set.of("ROLE_SUPER", "ROLE_MANAGER", "ROLE_STAFF")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_BOARD_EXAMPLE_DELETE,
            ADMIN_BOARD_EXAMPLE,
            AppType.ADMIN,
            "/board/exampleBoard/delete",
            false,
            "삭제",
            null,
            null,
            null,
            null,
            null,
            Set.of("ROLE_SUPER", "ROLE_MANAGER", "ROLE_STAFF")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_SETTINGS_ADMIN_DELETE,
            ADMIN_SETTINGS_ADMIN,
            AppType.ADMIN,
            "/settings/admins/delete",
            false,
            "삭제",
            null,
            null,
            null,
            null,
            null,
            Set.of("ROLE_SUPER", "ROLE_MANAGER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_BOARD_EXAMPLE_EXCEL,
            ADMIN_BOARD_EXAMPLE,
            AppType.ADMIN,
            "/board/exampleBoard/excel",
            false,
            "엑셀 다운로드",
            null,
            null,
            null,
            null,
            null,
            Set.of("ROLE_SUPER", "ROLE_MANAGER", "ROLE_STAFF")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_SETTINGS_ADMIN_EXCEL,
            ADMIN_SETTINGS_ADMIN,
            AppType.ADMIN,
            "/settings/admins/excel",
            false,
            "엑셀 다운로드",
            null,
            null,
            null,
            null,
            null,
            Set.of("ROLE_SUPER", "ROLE_MANAGER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_SETTINGS_URI_FRONT,
            ADMIN_SETTINGS,
            AppType.ADMIN,
            "/settings/uri/FRONT",
            true,
            "URI 관리 (FRONT)",
            null,
            "URI",
            "URI 관리 화면",
            "uri",
            null,
            Set.of("ROLE_SUPER", "ROLE_MANAGER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_SETTINGS_CODE,
            ADMIN_SETTINGS,
            AppType.ADMIN,
            "/settings/uicode",
            true,
            "UI 코드 관리",
            null,
            "UI 코드",
            "UI 코드 관리 페이지",
            "uicode",
            null,
            Set.of("ROLE_SUPER", "ROLE_MANAGER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_ANALYSIS,
            ADMIN,
            AppType.ADMIN,
            "/analysis",
            true,
            "Analysis",
            null,
            "analysis",
            "analysis",
            " analysis",
            null,
            Set.of("ROLE_SUPER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_ANALYSIS_OPERATION_LOG,
            ADMIN_ANALYSIS,
            AppType.ADMIN,
            "/analysis/operationlog",
            true,
            "Operation Log (수행로그)",
            null,
            "Log",
            "Log",
            " Log",
            null,
            Set.of("ROLE_SUPER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_ANALYSIS_OPERATION_LOG_UPDATE,
            ADMIN_ANALYSIS_OPERATION_LOG,
            AppType.ADMIN,
            "/analysis/operationlog/{{pathVar}}",
            false,
            "수행로그 상세",
            null,
            "Log",
            "Log",
            " Log",
            null,
            Set.of("ROLE_SUPER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_ANALYSIS_OPERATION_LOG_EXCEL,
            ADMIN_ANALYSIS_OPERATION_LOG,
            AppType.ADMIN,
            "/analysis/operationlog/excel",
            false,
            "수행로그 Excel",
            null,
            "Log",
            "Log",
            " Log",
            null,
            Set.of("ROLE_SUPER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_ADD_ONS,
            ADMIN,
            AppType.ADMIN,
            "/add-ons",
            true,
            "Add-ons (부가기능)",
            null,
            "Add-ons",
            "Add-ons",
            " Add-ons",
            null,
            Set.of("ROLE_SUPER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_ADD_ONS_SEND_EMAIL,
            ADMIN_ADD_ONS,
            AppType.ADMIN,
            "/addons/email/form",
            true,
            "Email 발송",
            null,
            "Email",
            "Email",
            "email",
            null,
            Set.of("ROLE_SUPER")),
        null,
        false);

    uriSavePortIn.connect(
        new URISaveCommand(
            ADMIN_ADD_ONS_SEND_EMAIL_CREATE,
            ADMIN_ADD_ONS_SEND_EMAIL,
            AppType.ADMIN,
            "/addons/email/form",
            false,
            "Email 입력폼",
            null,
            "Email Form",
            "Email Form",
            "email",
            null,
            Set.of("ROLE_SUPER")),
        null,
        false);
  }
}
