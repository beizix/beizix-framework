package org.beizix.core.config.adapter.web.interceptor.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class URITopTierVO {
  private String id;
  private String text;
  private String uri;
  private Boolean showOnNavi;
  private List<URITopTierVO> children;
}
