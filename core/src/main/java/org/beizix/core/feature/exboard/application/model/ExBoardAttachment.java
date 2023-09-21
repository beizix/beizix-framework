package org.beizix.core.feature.exboard.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.core.feature.fileUpload.application.model.FileUploadInfo;

import javax.persistence.Embedded;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExBoardAttachment {
  private Long id;
  @Embedded private FileUploadInfo fileUploadInfo;
  private ExBoard exBoard;
}
