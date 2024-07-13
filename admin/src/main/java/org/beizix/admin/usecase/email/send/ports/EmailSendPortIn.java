package org.beizix.admin.usecase.email.send.ports;

import org.beizix.admin.usecase.email.send.ports.application.domain.Email;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailSendPortIn {
  void connect(Email email) throws MessagingException, UnsupportedEncodingException;
}
