package app.module.core.usecase.uri.save.adapter.web;

import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import app.module.core.config.application.enums.AppType;
import app.module.core.config.application.enums.SaveType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class URISaveBindingVO {
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

  private MultipartFile ogImageFile;

  private Set<String> roles;

  private SaveType saveType;
}
