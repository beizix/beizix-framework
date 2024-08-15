package app.module.core.usecase.uri.toptier.application.domain;

import java.util.List;

import app.module.core.config.application.enums.AppType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
