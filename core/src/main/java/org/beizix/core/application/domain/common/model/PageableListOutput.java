package org.beizix.core.application.domain.common.model;

/** 페이징 처리된 목록 정보 규격 */
public interface PageableListOutput {
  /**
   * 페이지네이션 처리 결과 PageableBase 객체 반환
   *
   * @return 페이지네이션 처리 결과
   */
  PageableOutput getPageable();
}
