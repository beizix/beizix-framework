package org.beizix.admin.usecase.exboard.save.adapters.web.model;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ExBoardCreateBindingVO {

  private Boolean visible;

  @NotBlank(message = "{valid.common.required}")
  private String title;

  @NotBlank(message = "{valid.common.required}")
  private String content;

  @NotNull(message = "{valid.common.required}")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime boardStartDate;

  @NotNull(message = "{valid.common.required}")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime boardEndDate;

  private String repImgAlt; // 대표 이미지 - 대체 텍스트
  private Integer orderNo;
}
