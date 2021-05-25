package org.unq.pokerplanning.config.exception;

import org.unq.pokerplanning.config.ErrorCode;

public final class NotFoundException extends GenericException {

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

}
