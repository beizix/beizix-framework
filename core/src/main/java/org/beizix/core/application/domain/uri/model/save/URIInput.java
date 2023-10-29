package org.beizix.core.application.domain.uri.model.save;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.config.enums.AppType;

@Getter
@Setter
@AllArgsConstructor
public class URIInput {
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
