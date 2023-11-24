package org.beizix.core.application.domain.uri.model.matchparent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class URIMatchParentVO {
  private String id;
  private String parentId;
  private String uri;
  private String text;
}
