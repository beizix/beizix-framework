package org.beizix.core.application.domain.uri.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class URISortInput {
  private List<URISortInput> updateList;
  private String id;
  private Integer orderNo;
}
