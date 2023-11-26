package org.beizix.core.usecase.uri.toptier.application.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.configuration.application.enums.AppType;

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
