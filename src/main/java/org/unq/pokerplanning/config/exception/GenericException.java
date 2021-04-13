package org.unq.pokerplanning.config.exception;

import org.unq.pokerplanning.config.ErrorCode;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;

public abstract class GenericException extends RuntimeException {

    private static final String SPACE = " ";
    private static final String COMMA = ",";
    private final ErrorCode errorCode;

    public GenericException(ErrorCode errorCode) {
        super(errorCode.getReasonPhrase());
        this.errorCode = errorCode;
    }

    public GenericException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getReasonPhrase(), cause);
        this.errorCode = errorCode;
    }

    public GenericException(ErrorCode errorCode, String message) {
        super(buildMessage(message, errorCode));
        this.errorCode = errorCode;
    }

    public GenericException(ErrorCode errorCode, String message, Throwable cause) {
        super(buildMessage(message, errorCode), cause);
        this.errorCode = errorCode;
    }

    public GenericException(String message, Throwable cause) {
        super(buildMessage(message, ErrorCode.BAD_SQL_FORMAT), cause);
        this.errorCode = ErrorCode.BAD_SQL_FORMAT;
    }
    public ErrorCode getCode() {
        return this.errorCode;
    }

    private static String buildMessage(String message, ErrorCode errorCode) {
        return errorCode.getReasonPhrase() + COMMA + SPACE + message;
    }

}
