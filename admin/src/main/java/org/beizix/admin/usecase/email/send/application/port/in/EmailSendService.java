package org.beizix.admin.usecase.email.send.application.port.in;

import java.io.UnsupportedEncodingException;
import java.util.Objects;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.email.send.application.domain.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
class EmailSendService implements EmailSendPortIn {
  private final JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String mailUsername;

  @Override
  public void connect(Email email) throws MessagingException, UnsupportedEncodingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    mimeMessageHelper.setTo(email.getTo());
    mimeMessageHelper.setFrom(email.getFrom() != null ? email.getFrom() : mailUsername);
    mimeMessageHelper.setSubject(MimeUtility.encodeText(email.getSubject(), "UTF-8", "B"));
    mimeMessageHelper.setText(email.getMessage(), "HTML".equals(email.getMsgType()));

    if (email.getMultipartFiles() != null)
      for (MultipartFile multipartFile : email.getMultipartFiles()) {
        if (!multipartFile.isEmpty()) {
          mimeMessageHelper.addAttachment(
              Objects.requireNonNull(multipartFile.getOriginalFilename()), multipartFile);
        }
      }

    mailSender.send(mimeMessage);
  }
}
