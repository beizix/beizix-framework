package org.beizix.admin.usecase.uri.view.adapter.web;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.beizix.core.config.application.enums.AppType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class URIBindingVO {
  private String id;
  private AppType appType;
  private String text;
  private String uri;
  private String parentId;
  private Integer orderNo;
  private boolean showOnNavi;
  private String ogTitle;
  private String ogDesc;
  private String ogKeywords;
  private String ogImage;
  private Set<String> roles;
}
