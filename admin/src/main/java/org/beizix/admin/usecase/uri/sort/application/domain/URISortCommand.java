package org.beizix.admin.usecase.uri.sort.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class URISortCommand {
  private String id;
  private Integer orderNo;
}
