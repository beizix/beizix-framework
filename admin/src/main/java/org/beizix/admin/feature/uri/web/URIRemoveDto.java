package org.beizix.admin.feature.uri.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class URIRemoveDto {
  @NotEmpty(message = "{valid.common.required}")
  private String id;
}
