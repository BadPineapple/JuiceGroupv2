package ilion.util;

public class Recaptcha {

	boolean success;
    String challenge_ts;
    String hostname;
    String errorsCode;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorsCode() {
        return errorsCode;
    }

    public void setErrorsCode(String errorsCode) {
        this.errorsCode = errorsCode;
    }
	
}