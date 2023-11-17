package org.beizix.admin.adapter.web.uri.model.tree;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class URITreeVO {
  private String id;
  private String text;
  private String uri;
  private List<URITreeVO> nodes;
}
