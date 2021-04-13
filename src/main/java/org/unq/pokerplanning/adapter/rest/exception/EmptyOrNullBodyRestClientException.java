package org.unq.pokerplanning.adapter.rest.exception;

import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.config.exception.GenericException;

public final class EmptyOrNullBodyRestClientException extends GenericException {

    public EmptyOrNullBodyRestClientException(ErrorCode errorCode) {
        super(errorCode);
    }

}
