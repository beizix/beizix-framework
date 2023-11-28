package org.beizix.core.config.application.component;

import java.util.List;

/** 페이징 처리된 목록 정보 규격 */
public interface PageableListOutput<E extends AuditOutput> {
  /**
   * AuditOutput 정보를 담은 아이템 목록
   *
   * @return AuditOutput 를 구현한 아이템 목록
   */
  List<E> getContents();
  /**
   * 페이지네이션 처리 결과 PageableBase 객체 반환
   *
   * @return 페이지네이션 처리 결과
   */
  PageableOutput getPageable();
}
