package org.beizix.admin.usecase.email.send.application.port.in;

import org.beizix.admin.usecase.email.send.application.domain.Email;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailSendPortIn {
  void connect(Email email) throws MessagingException, UnsupportedEncodingException;
}
