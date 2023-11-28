package org.beizix.admin.config.application.exception;

public class AlreadyExistsRoleException extends RuntimeException {
  public AlreadyExistsRoleException(String message) {
    super(message);
  }
}
