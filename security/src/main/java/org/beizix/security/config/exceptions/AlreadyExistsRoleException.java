package org.beizix.security.config.exceptions;

public class AlreadyExistsRoleException extends RuntimeException {
  public AlreadyExistsRoleException(String message) {
    super(message);
  }
}
