package ilion.util.exceptions;

public class NotEmptyAndNotNullException extends Exception {

  private static final long serialVersionUID = 1L;

  public NotEmptyAndNotNullException() {
  }

  public NotEmptyAndNotNullException(String message) {
    super(message);
  }

  public NotEmptyAndNotNullException(Throwable cause) {
    super(cause);
  }

  public NotEmptyAndNotNullException(String message, Throwable cause) {
    super(message, cause);
  }

}
