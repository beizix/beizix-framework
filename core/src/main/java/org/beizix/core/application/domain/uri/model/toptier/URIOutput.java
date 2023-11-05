package org.beizix.core.application.domain.uri.model.toptier;

import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.config.enums.AppType;

@Getter
@Setter
@AllArgsConstructor
public class URIOutput {
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
  private List<URIOutput> nodes;
  private Set<String> roles;
  private Integer depth;
}
