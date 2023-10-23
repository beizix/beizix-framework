package org.beizix.admin.adapter.web.uri;

import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
public class URIDto {
  private String id;
  private AppType appType;

  @NotEmpty(message = "{valid.uri.text}")
  private String text;

  @NotEmpty(message = "{valid.uri.uri}")
  @Pattern(regexp = "^(/)|(/).+", message = "{valid.uri.must.startWith.slash}")
  @Pattern(regexp = "^(/)|.*[^/]$", message = "{valid.uri.mustNot.endWith.slash}")
  private String uri;

  private String type;

  @NotEmpty(message = "{valid.common.required}")
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
