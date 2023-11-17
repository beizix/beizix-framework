package org.beizix.core.application.domain.uri.model.toptier;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.config.enums.AppType;

@Getter
@Setter
@AllArgsConstructor
public class URITopTierOutput {
  private String id;
  private AppType appType;
  private String text;
  private String uri;
  private Boolean showOnNavi;
  private List<URITopTierOutput> children;
}
