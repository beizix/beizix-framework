package org.beizix.core.application.domain.uri.model.list;

import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.core.config.enums.AppType;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
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
  private MultipartFile ogImageFile;

  private List<URIOutput> nodes;
  private Set<String> roles;
  private int depth;
}
