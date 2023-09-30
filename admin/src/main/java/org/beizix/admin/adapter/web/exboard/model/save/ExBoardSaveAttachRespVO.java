package org.beizix.admin.adapter.web.exboard.model.save;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.beizix.core.adapter.web.common.model.AuditVO;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;
import org.beizix.core.application.domain.fileupload.model.FileUploadInfo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExBoardSaveAttachRespVO extends AuditVO {
  private Long id;
  private FileUploadInfo fileUploadInfo;
  private ExBoardSaveInput exBoard;
}
