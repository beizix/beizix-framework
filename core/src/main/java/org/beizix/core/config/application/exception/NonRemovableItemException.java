package org.beizix.core.config.application.exception;

public class NonRemovableItemException extends RuntimeException {
  public NonRemovableItemException(String message) {
    super(message);
  }
}
