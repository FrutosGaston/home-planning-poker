package org.unq.pokerplanning.adapter.rest.exception;

import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.config.exception.GenericException;

public final class NonTargetRestClientException extends GenericException {

    public NonTargetRestClientException(ErrorCode errorCode) {
        super(errorCode);
    }

}
