package app.module.core.config.application.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PublicAccess {
  STATIC("정적 자원", "/static/**"),
  PUBLIC_INLINE("공개파일 inline 참조", "/content-disposition/inline/**"),
  PUBLIC_ATTACH("공개파일 attachment 참조", "/content-disposition/attachment/**"),
  CACHE_REFRESH("캐시 리프레시", "/refresh/cacheable/**"),
  PUBLIC_API("공개 API", "/api/public/**"),
  ERROR("에러 페이지", "/error"),
  FAVICON("파비콘", "/favicon.ico"),
  LOGIN("로그인 페이지", "/login"),
  LOGOUT("로그아웃", "/logout"),
  INTERCEPTOR_ERROR("인터셉터 에러 포워딩", "/error/interceptor");

  private final String desc;
  private final String path;
}
