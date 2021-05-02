package org.unq.pokerplanning.application.port.out;

import org.unq.pokerplanning.domain.GuestUser;

public interface GuestUserMessenger {
    void created(Integer roomId, GuestUser guestUser);
}
