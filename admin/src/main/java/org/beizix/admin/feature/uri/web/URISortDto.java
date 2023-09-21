package org.beizix.admin.feature.uri.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class URISortDto {
  @NotNull(message = "{valid.common.required}")
  @Size(min = 2, max = 2, message = "{valid.uri.sort.size}")
  private List<URISortDto> updateList;

  @NotBlank(message = "{valid.common.required}")
  private String id;

  private Integer orderNo;
}
