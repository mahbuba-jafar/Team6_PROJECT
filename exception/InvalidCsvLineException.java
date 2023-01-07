package exception;

import enums.ErrorCodeEnum;

public class InvalidCsvLineException extends RuntimeException {
    private final String message;
    private final int code;

    public InvalidCsvLineException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.message = errorCodeEnum.getMessage();
        this.code = errorCodeEnum.getCode();
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
