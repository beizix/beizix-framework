package org.beizix.core.application.domain.common.model;

/** 페이징 처리된 목록 정보 규격 */
public interface PageableListOutput {

  /**
   * 전체 레코딩 수 반환
   *
   * @return 전체 data 수
   */
  long getTotalElements();

  /**
   * 페이지별 사이즈(pageSize)로 분할된 전체 페이지 수
   *
   * @return 전체 page 수
   */
  int getTotalPages();

  /**
   * 페이지네이션 처리 결과 PageableBase 객체 반환
   *
   * @return 페이지네이션 처리 결과
   */
  PageableBase getPageable();
}
