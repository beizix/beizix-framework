package org.beizix.admin.adapter.web.admin.model.query;

import lombok.Data;

import java.util.Collection;

@Data
public class AdminListReqParam {
  private String searchField;
  private String searchValue;
  private String searchRole;
  private Integer page;
  private Integer size;
  private String sort;
  private Collection<String> checkedIds;
}
