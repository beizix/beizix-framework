package org.beizix.admin.adapter.web.uri.model.view;

import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
public class URIBindingVO {
  private String id;
  private AppType appType;
  private String text;
  private String uri;
  private String type;
  private String parentId;
  private Integer orderNo;
  private boolean showOnNavi;
  private String ogTitle;
  private String ogDesc;
  private String ogKeywords;
  private String ogImage;
  private String mode;
  private MultipartFile ogImageFile;
  private Set<String> roles;
}
