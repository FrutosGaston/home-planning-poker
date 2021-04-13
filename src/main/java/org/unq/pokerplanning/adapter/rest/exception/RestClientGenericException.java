package org.unq.pokerplanning.adapter.rest.exception;

import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.config.exception.GenericException;

public final class RestClientGenericException extends GenericException {

    public RestClientGenericException(ErrorCode errorCode) {
        super(errorCode);
    }

}
