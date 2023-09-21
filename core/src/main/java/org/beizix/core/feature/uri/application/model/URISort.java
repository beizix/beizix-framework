package org.beizix.core.feature.uri.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class URISort {
  private List<URISort> updateList;
  private String id;
  private Integer orderNo;
}
