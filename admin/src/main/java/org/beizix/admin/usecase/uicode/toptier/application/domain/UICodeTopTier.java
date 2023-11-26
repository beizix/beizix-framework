package org.beizix.admin.usecase.uicode.toptier.application.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UICodeTopTier {
  private String id;
  private String codeText;
  private Integer orderNo;
  private List<UICodeTopTier> nodes;
}
