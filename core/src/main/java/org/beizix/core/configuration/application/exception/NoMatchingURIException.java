package org.beizix.core.configuration.application.exception;

public class NoMatchingURIException extends RuntimeException {
  public NoMatchingURIException(String message) {
    super(message);
  }

  public NoMatchingURIException(String message, Throwable cause) {
    super(message, cause);
  }
}
