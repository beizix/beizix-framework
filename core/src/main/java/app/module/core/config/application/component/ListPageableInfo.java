package app.module.core.config.application.component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 상세 화면에서 목록 페이지의 페이징 정보를 기억하기 위함.
 */
@Getter
@Setter
@NoArgsConstructor
public class ListPageableInfo {
  private int page;
  private String sort;
}
