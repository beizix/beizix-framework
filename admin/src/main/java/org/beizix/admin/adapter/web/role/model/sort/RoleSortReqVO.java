package org.beizix.admin.adapter.web.role.model.sort;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleSortReqVO {
  private String id;
  private Integer orderNo;
  private List<RoleSortReqVO> updateList;
}
