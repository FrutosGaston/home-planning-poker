package org.unq.pokerplanning.application.port.in;

import org.unq.pokerplanning.domain.GuestUser;

public interface CreateGuestUserCommand {
    GuestUser execute(GuestUser guestUser);
}
