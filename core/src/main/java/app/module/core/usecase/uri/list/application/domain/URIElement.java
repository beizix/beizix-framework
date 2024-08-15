package app.module.core.usecase.uri.list.application.domain;

import java.util.Set;

import app.module.core.config.application.enums.AppType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class URIElement {

  private String id;
  private AppType appType;
  private String parentId;
  private Integer orderNo;
  private String text;
  private String uri;
  private Boolean showOnNavi;
  private String ogTitle;
  private String ogDesc;
  private String ogKeywords;
  private String ogImage;
  private Set<String> roles;
}
