package org.beizix.admin.adapter.web.role.model.save;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.utility.enums.OperationType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleSaveRespVO {
  private String id;
  private String description;
  private Integer orderNo;
  private OperationType operationType;
}
