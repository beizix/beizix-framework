package org.beizix.security.application.domain.admin.model.filter;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminListInput {
  private String searchField;
  private String searchValue;
  private String searchRole;

  private Integer page;
  private Integer size;
  private String sort;

  private List<String> checkedIds;
}
