package org.beizix.core.feature.email.application.service;

import org.beizix.core.feature.email.application.model.Email;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailSendService {
  void operate(Email email) throws MessagingException, UnsupportedEncodingException;
}
