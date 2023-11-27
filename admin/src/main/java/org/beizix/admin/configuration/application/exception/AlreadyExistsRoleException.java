package org.beizix.admin.configuration.application.exception;

public class AlreadyExistsRoleException extends RuntimeException {
  public AlreadyExistsRoleException(String message) {
    super(message);
  }
}
