package org.beizix.core.configuration.application.exception;

public class NonRemovableItemException extends RuntimeException {
  public NonRemovableItemException(String message) {
    super(message);
  }
}
