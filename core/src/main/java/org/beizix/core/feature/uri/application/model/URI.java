package org.beizix.core.feature.uri.application.model;

import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.beizix.core.config.enums.AppType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class URI {
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

  private List<URI> nodes;
  private Set<String> roles;
  private int depth;
}
