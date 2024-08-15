package app.module.admin.usecase.email.send.adapters.web.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
public class EmailVO {
  @NotEmpty(message = "{valid.common.required}")
  private String to;

  @NotEmpty(message = "{valid.common.required}")
  private String subject;

  private String msgType;

  @NotEmpty(message = "{valid.common.required}")
  private String message;

  private List<MultipartFile> multipartFiles;
}
