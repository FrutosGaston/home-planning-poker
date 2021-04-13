package org.unq.pokerplanning.adapter.rest.exception;

import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.config.exception.GenericException;

public final class NotFoundRestClientException extends GenericException {

    public NotFoundRestClientException(ErrorCode errorCode) {
        super(errorCode);
    }
}
