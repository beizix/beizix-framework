package org.beizix.core.application.domain.uri.model.list;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.beizix.core.config.enums.AppType;

@Data
@Builder
@AllArgsConstructor
public class URIViewOutput {
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
