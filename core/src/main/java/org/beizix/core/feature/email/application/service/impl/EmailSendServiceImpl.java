package org.beizix.core.feature.email.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.beizix.core.feature.email.application.model.Email;
import org.beizix.core.feature.email.application.service.EmailSendService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class EmailSendServiceImpl implements EmailSendService {
  private final JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String mailUsername;

  @Override
  public void operate(Email email) throws MessagingException, UnsupportedEncodingException {
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
