package org.unq.pokerplanning.adapter.rest.exception;

import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.config.exception.GenericException;

public final class TimeoutRestClientException extends GenericException {

    public TimeoutRestClientException(ErrorCode errorCode) {
        super(errorCode);
    }

}
