package org.unq.pokerplanning.application.port.out;

import org.unq.pokerplanning.domain.User;

public interface GuestUserRepository {
    User getUser(Long id);

    Integer createGuestUser(User user, Long roomId);
}
