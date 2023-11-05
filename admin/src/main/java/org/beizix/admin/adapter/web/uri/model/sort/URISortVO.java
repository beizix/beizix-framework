package org.beizix.admin.adapter.web.uri.model.sort;

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
public class URISortVO {
  @NotBlank(message = "{valid.common.required}")
  private String id;

  private Integer orderNo;
}
