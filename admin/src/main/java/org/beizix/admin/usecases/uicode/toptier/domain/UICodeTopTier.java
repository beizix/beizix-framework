package org.beizix.admin.usecases.uicode.toptier.domain;

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
  private List<UICodeTopTier> nodes;
}
