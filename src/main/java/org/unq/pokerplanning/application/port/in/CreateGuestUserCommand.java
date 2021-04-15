package org.unq.pokerplanning.application.port.in;

import org.unq.pokerplanning.domain.GuestUser;

public interface CreateGuestUserCommand {
    Integer execute(GuestUser guestUser);
}
