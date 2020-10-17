package kr.chis.webfluxapi.exception;

public class BookException extends Exception{

    private static final long serialVersionUID = 8730945814183041463L;

    private String errorCode;

    public BookException(String errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
