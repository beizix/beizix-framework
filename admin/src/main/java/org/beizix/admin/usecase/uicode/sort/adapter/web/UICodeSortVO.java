package org.beizix.admin.usecase.uicode.sort.adapter.web;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UICodeSortVO {
  private List<UICodeSortCompVO> updateList;
}
