package org.beizix.core.application.port.in.email;

import org.beizix.core.application.domain.email.model.Email;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailSendPortIn {
  void connect(Email email) throws MessagingException, UnsupportedEncodingException;
}
