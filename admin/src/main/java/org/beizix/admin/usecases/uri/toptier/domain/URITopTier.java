package org.beizix.admin.usecases.uri.toptier.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.config.enums.AppType;

@Getter
@Setter
@AllArgsConstructor
public class URITopTier {
  private String id;
  private AppType appType;
  private String text;
  private String uri;
  private Boolean showOnNavi;
  private Integer orderNo;
  private List<URITopTier> children;
}
