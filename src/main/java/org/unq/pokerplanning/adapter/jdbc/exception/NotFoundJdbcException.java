package org.unq.pokerplanning.adapter.jdbc.exception;

import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.config.exception.GenericException;

public final class NotFoundJdbcException extends GenericException {

    public NotFoundJdbcException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public NotFoundJdbcException(ErrorCode errorCode) {
        super(errorCode);
    }

}

