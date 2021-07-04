package ilion.util.exceptions;

public class AlreadyExistsException extends Exception {

  private static final long serialVersionUID = 1L;

  public AlreadyExistsException() {
  }

  public AlreadyExistsException(String message) {
    super(message);
  }

  public AlreadyExistsException(Throwable cause) {
    super(cause);
  }

  public AlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }

}
