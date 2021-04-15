package org.unq.pokerplanning.application.port.out;

import org.unq.pokerplanning.domain.GuestUser;

public interface GuestUserRepository {
    GuestUser getUser(Long id);

    Integer createGuestUser(GuestUser guestUser);
}
