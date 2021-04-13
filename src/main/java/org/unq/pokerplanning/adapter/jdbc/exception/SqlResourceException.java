package org.unq.pokerplanning.adapter.jdbc.exception;

import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.config.exception.GenericException;

public final class SqlResourceException extends GenericException {

    public SqlResourceException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public SqlResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
