package org.beizix.admin.usecase.uri.save.application.domain;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.config.application.enums.AppType;

@Getter
@Setter
@AllArgsConstructor
public class URISaveCommand {
  private String id;
  private String parentId;
  private AppType appType;
  private String uri;
  private Boolean showOnNavi;
  private String text;
  private Integer orderNo;
  private String ogTitle;
  private String ogDesc;
  private String ogKeywords;
  private String ogImage;
  private Set<String> roles;
}
