package junction.global.infra.exception.error;


public class JunctionException extends RuntimeException {

    private final ErrorCode errorCode;

    public JunctionException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public JunctionException(ErrorCode errorCode, String detailMessage) {
        super(errorCode.getMessage() + " → " + detailMessage);
        this.errorCode = errorCode;
    }


    public JunctionException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public int getHttpStatusCode() {
        return errorCode.getHttpCode();
    }
}
