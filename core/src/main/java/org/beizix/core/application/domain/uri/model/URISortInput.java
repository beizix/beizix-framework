package org.beizix.core.application.domain.uri.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class URISortInput {
  private String id;
  private Integer orderNo;
}
