package enums;

public enum ErrorCodeEnum {
    UNKNOWN_ERROR(1000, "Unknown error"),
    INVALID_INPUT(1001,"you must input one of the character shown"),
    INVALID_CSV_LINE(1002,"Invalid CSV line"),
    NOT_FOUND(1003,"No product matching the entered symbol was found");
    private final int code;
    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}