package app.module.admin.usecase.uri.toptier.adapter.web;

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
  private Integer orderNo;
  private List<URITreeVO> nodes;
}
