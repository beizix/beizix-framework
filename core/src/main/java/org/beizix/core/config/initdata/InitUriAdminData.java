package org.beizix.core.config.initdata;

import java.io.IOException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.beizix.core.config.enums.AppType;
import org.beizix.core.feature.uri.application.model.URI;
import org.beizix.core.feature.uri.application.service.URICreateUpdateService;
import org.beizix.utility.common.PropertyUtil;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(2)
public class InitUriAdminData implements ApplicationRunner {
  private final URICreateUpdateService uriCreateUpdateService;

  @Override
  public void run(ApplicationArguments args) throws IOException {
    if (!PropertyUtil.isCoreDataRequired()) return;

    final String ADMIN = "uri.admin";

    final String ADMIN_BOARD = "uri.admin.board";
    final String ADMIN_BOARD_EXAMPLE = "uri.admin.board.exampleBoard";
    final String ADMIN_BOARD_EXAMPLE_CREATE = "uri.admin.board.exampleBoard.create";
    final String ADMIN_BOARD_EXAMPLE_UPDATE = "uri.admin.board.exampleBoard.update.{{pathVars}}";
    final String ADMIN_BOARD_EXAMPLE_DELETE = "uri.admin.board.exampleBoard.delete";
    final String ADMIN_BOARD_EXAMPLE_EXCEL = "uri.admin.board.exampleBoard.excel";

    final String ADMIN_SETTINGS = "uri.admin.settings";

    final String ADMIN_SETTINGS_ADMIN_GROUP = "uri.admin.settings.admins.group";
    final String ADMIN_SETTINGS_ADMIN = "uri.admin.settings.admins";
    final String ADMIN_SETTINGS_ADMIN_CREATE = "uri.admin.settings.admins.create";
    final String ADMIN_SETTINGS_ADMIN_UPDATE = "uri.admin.settings.admins.update.{{pathVars}}";
    final String ADMIN_SETTINGS_ADMIN_DELETE = "uri.admin.settings.admins.delete";
    final String ADMIN_SETTINGS_ADMIN_EXCEL = "uri.admin.settings.admins.excel";
    final String ADMIN_SETTINGS_ADMIN_ROLE = "uri.admin.settings.adminRoles";

    final String ADMIN_SETTINGS_URI_ADMIN = "uri.admin.settings.uri.admin";
    final String ADMIN_SETTINGS_URI_FRONT = "uri.admin.settings.uri.front";
    final String ADMIN_SETTINGS_CODE = "uri.admin.settings.uicode";

    final String ADMIN_ANALYSIS = "uri.admin.analysis";
    final String ADMIN_ANALYSIS_OPERATION_LOG = "uri.admin.analysis.operationLog";
    final String ADMIN_ANALYSIS_OPERATION_LOG_UPDATE =
        "uri.admin.analysis.operationLog.{{pathVars}}";
    final String ADMIN_ANALYSIS_OPERATION_LOG_EXCEL = "uri.admin.analysis.operationLog.excel";

    final String ADMIN_ADD_ONS = "uri.admin.addons";
    final String ADMIN_ADD_ONS_SEND_EMAIL = "uri.admin.addons.email";
    final String ADMIN_ADD_ONS_SEND_EMAIL_CREATE = "uri.admin.addons.email.create";

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN)
            .parentId(null)
            .appType(AppType.ADMIN)
            .text("관리자")
            .uri("/")
            .showOnNavi(true)
            .ogTitle("어드민 홈")
            .ogDesc("어드민 홈 페이지")
            .ogKeywords("admin")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_BOARD)
            .parentId(ADMIN)
            .appType(AppType.ADMIN)
            .text("게시판")
            .uri("/board")
            .showOnNavi(true)
            .roles(Set.of("ROLE_SUPER"))
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_SETTINGS)
            .parentId(ADMIN)
            .appType(AppType.ADMIN)
            .text("Settings")
            .uri("/settings")
            .showOnNavi(true)
            .roles(Set.of("ROLE_MANAGER"))
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_SETTINGS_ADMIN_GROUP)
            .parentId(ADMIN_SETTINGS)
            .appType(AppType.ADMIN)
            .text("관리자 & 권한")
            .uri("/settings/adminGroup")
            .showOnNavi(true)
            .roles(Set.of("ROLE_MANAGER"))
            .ogTitle("org.beizix - 관리자")
            .ogDesc("org.beizix 관리자 목록 화면")
            .ogKeywords("org.beizix,notice,list")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_SETTINGS_ADMIN)
            .parentId(ADMIN_SETTINGS_ADMIN_GROUP)
            .appType(AppType.ADMIN)
            .text("관리자")
            .uri("/settings/admins")
            .showOnNavi(true)
            .roles(Set.of("ROLE_MANAGER"))
            .ogTitle("org.beizix - 관리자")
            .ogDesc("org.beizix 관리자 목록 화면")
            .ogKeywords("org.beizix,notice,list")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_SETTINGS_ADMIN_CREATE)
            .parentId(ADMIN_SETTINGS_ADMIN)
            .appType(AppType.ADMIN)
            .text("등록")
            .uri("/settings/admins/create")
            .showOnNavi(false)
            .roles(Set.of("ROLE_MANAGER"))
            .ogTitle("org.beizix - 관리자")
            .ogDesc("org.beizix 관리자 등록 화면")
            .ogKeywords("org.beizix,notice,create")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_SETTINGS_ADMIN_UPDATE)
            .parentId(ADMIN_SETTINGS_ADMIN)
            .appType(AppType.ADMIN)
            .text("수정")
            .uri("/settings/admins/update/{{pathVars}}")
            .showOnNavi(false)
            .roles(Set.of("ROLE_MANAGER"))
            .ogTitle("org.beizix - 관리자")
            .ogDesc("org.beizix 관리자 수정 화면")
            .ogKeywords("org.beizix,notice,update")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_SETTINGS_ADMIN_ROLE)
            .parentId(ADMIN_SETTINGS_ADMIN_GROUP)
            .appType(AppType.ADMIN)
            .text("관리자 권한")
            .uri("/settings/adminRoles")
            .showOnNavi(true)
            .roles(Set.of("ROLE_MANAGER"))
            .ogTitle("org.beizix - 관리자 권한")
            .ogDesc("org.beizix 관리자 권한 목록 화면")
            .ogKeywords("org.beizix,role,authorities")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_SETTINGS_URI_ADMIN)
            .parentId(ADMIN_SETTINGS)
            .appType(AppType.ADMIN)
            .text("URI 관리 (관리자)")
            .uri("/settings/uri/ADMIN")
            .showOnNavi(true)
            .roles(Set.of("ROLE_MANAGER"))
            .ogTitle("org.beizix - URI")
            .ogDesc("org.beizix URI 관리 화면")
            .ogKeywords("org.beizix,uri")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_BOARD_EXAMPLE)
            .parentId(ADMIN_BOARD)
            .appType(AppType.ADMIN)
            .text("예제 게시판")
            .uri("/board/exampleBoard")
            .showOnNavi(true)
            .roles(Set.of("ROLE_STAFF"))
            .ogTitle("org.beizix - 예제 게시판")
            .ogDesc("org.beizix 예제 게시판 관리 페이지")
            .ogKeywords("org.beizix,notice")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_BOARD_EXAMPLE_CREATE)
            .parentId(ADMIN_BOARD_EXAMPLE)
            .appType(AppType.ADMIN)
            .text("등록")
            .uri("/board/exampleBoard/create")
            .showOnNavi(false)
            .roles(Set.of("ROLE_STAFF"))
            .ogTitle("org.beizix - 예제 게시판")
            .ogDesc("org.beizix 예제 게시판 등록 페이지")
            .ogKeywords("org.beizix,notice,create")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_BOARD_EXAMPLE_UPDATE)
            .parentId(ADMIN_BOARD_EXAMPLE)
            .appType(AppType.ADMIN)
            .text("수정")
            .uri("/board/exampleBoard/update/{{pathVars}}")
            .showOnNavi(false)
            .roles(Set.of("ROLE_STAFF"))
            .ogTitle("org.beizix - 예제 게시판")
            .ogDesc("org.beizix 예제 게시판 수정 페이지")
            .ogKeywords("org.beizix,notice,update")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_BOARD_EXAMPLE_DELETE)
            .parentId(ADMIN_BOARD_EXAMPLE)
            .appType(AppType.ADMIN)
            .text("삭제")
            .uri("/board/exampleBoard/delete")
            .showOnNavi(false)
            .roles(Set.of("ROLE_STAFF"))
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_SETTINGS_ADMIN_DELETE)
            .parentId(ADMIN_SETTINGS_ADMIN)
            .appType(AppType.ADMIN)
            .text("삭제")
            .uri("/settings/admins/delete")
            .showOnNavi(false)
            .roles(Set.of("ROLE_MANAGER"))
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_BOARD_EXAMPLE_EXCEL)
            .parentId(ADMIN_BOARD_EXAMPLE)
            .appType(AppType.ADMIN)
            .text("엑셀 다운로드")
            .uri("/board/exampleBoard/excel")
            .showOnNavi(false)
            .roles(Set.of("ROLE_STAFF"))
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_SETTINGS_ADMIN_EXCEL)
            .parentId(ADMIN_SETTINGS_ADMIN)
            .appType(AppType.ADMIN)
            .text("엑셀 다운로드")
            .uri("/settings/admins/excel")
            .showOnNavi(false)
            .roles(Set.of("ROLE_MANAGER"))
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_SETTINGS_URI_FRONT)
            .parentId(ADMIN_SETTINGS)
            .appType(AppType.ADMIN)
            .text("URI 관리 (FRONT)")
            .uri("/settings/uri/FRONT")
            .showOnNavi(true)
            .roles(Set.of("ROLE_MANAGER"))
            .ogTitle("org.beizix - URI")
            .ogDesc("org.beizix URI 관리 화면")
            .ogKeywords("org.beizix,uri")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_SETTINGS_CODE)
            .parentId(ADMIN_SETTINGS)
            .appType(AppType.ADMIN)
            .text("UI 코드 관리")
            .uri("/settings/uicode")
            .showOnNavi(true)
            .roles(Set.of("ROLE_MANAGER"))
            .ogTitle("org.beizix - UI 코드")
            .ogDesc("org.beizix UI 코드 관리 페이지")
            .ogKeywords("org.beizix,uicode")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_ANALYSIS)
            .parentId(ADMIN)
            .appType(AppType.ADMIN)
            .text("Analysis")
            .uri("/analysis")
            .showOnNavi(true)
            .roles(Set.of("ROLE_SUPER"))
            .ogTitle("org.beizix - analysis")
            .ogDesc("org.beizix analysis")
            .ogKeywords("org.beizix, analysis")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_ANALYSIS_OPERATION_LOG)
            .parentId(ADMIN_ANALYSIS)
            .appType(AppType.ADMIN)
            .text("Operation Log (수행로그)")
            .uri("/analysis/operationlog")
            .showOnNavi(true)
            .roles(Set.of("ROLE_SUPER"))
            .ogTitle("org.beizix - Log")
            .ogDesc("org.beizix Log")
            .ogKeywords("org.beizix, Log")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_ANALYSIS_OPERATION_LOG_UPDATE)
            .parentId(ADMIN_ANALYSIS_OPERATION_LOG)
            .appType(AppType.ADMIN)
            .text("수행로그 상세")
            .uri("/analysis/operationlog/{{pathVars}}")
            .showOnNavi(false)
            .roles(Set.of("ROLE_SUPER"))
            .ogTitle("org.beizix - Log")
            .ogDesc("org.beizix Log")
            .ogKeywords("org.beizix, Log")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_ANALYSIS_OPERATION_LOG_EXCEL)
            .parentId(ADMIN_ANALYSIS_OPERATION_LOG)
            .appType(AppType.ADMIN)
            .text("수행로그 Excel")
            .uri("/analysis/operationlog/excel")
            .showOnNavi(false)
            .roles(Set.of("ROLE_SUPER"))
            .ogTitle("org.beizix - Log")
            .ogDesc("org.beizix Log")
            .ogKeywords("org.beizix, Log")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_ADD_ONS)
            .parentId(ADMIN)
            .appType(AppType.ADMIN)
            .text("Add-ons (부가기능)")
            .uri("/add-ons")
            .showOnNavi(true)
            .roles(Set.of("ROLE_SUPER"))
            .ogTitle("org.beizix - Add-ons")
            .ogDesc("org.beizix Add-ons")
            .ogKeywords("org.beizix, Add-ons")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_ADD_ONS_SEND_EMAIL)
            .parentId(ADMIN_ADD_ONS)
            .appType(AppType.ADMIN)
            .text("Email 발송")
            .uri("/addons/email/form")
            .showOnNavi(true)
            .roles(Set.of("ROLE_SUPER"))
            .ogTitle("org.beizix - Email")
            .ogDesc("org.beizix Email")
            .ogKeywords("org.beizix,email")
            .build(),
        false);

    uriCreateUpdateService.operate(
        URI.builder()
            .id(ADMIN_ADD_ONS_SEND_EMAIL_CREATE)
            .parentId(ADMIN_ADD_ONS_SEND_EMAIL)
            .appType(AppType.ADMIN)
            .text("Email 입력폼")
            .uri("/addons/email/form")
            .showOnNavi(false)
            .roles(Set.of("ROLE_SUPER"))
            .ogTitle("org.beizix - Email Form")
            .ogDesc("org.beizix Email Form")
            .ogKeywords("org.beizix,email")
            .build(),
        false);
  }
}
