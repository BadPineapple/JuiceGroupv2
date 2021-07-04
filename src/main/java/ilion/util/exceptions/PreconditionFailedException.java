package ilion.util.exceptions;

public class PreconditionFailedException extends Exception {

  private static final long serialVersionUID = 1L;

  public PreconditionFailedException() {
    super();
  }

  public PreconditionFailedException(String message) {
    super(message);
  }

}
