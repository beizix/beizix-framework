package app.module.core.usecase.uri.ancestry.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class URIAncestry {
  private String id;
  private String parentId;
  private String uri;
  private String text;
}
