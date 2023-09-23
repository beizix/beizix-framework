package org.beizix.core.application.domain.email.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Email {
  private String to;
  private String from;
  private String subject;
  private String msgType;
  private String message;
  private List<MultipartFile> multipartFiles;
}
