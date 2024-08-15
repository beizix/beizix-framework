package app.module.core.config.application.exception;

public class AlreadyExistItemException extends RuntimeException {
  public AlreadyExistItemException(String message) {
    super(message);
  }
}
