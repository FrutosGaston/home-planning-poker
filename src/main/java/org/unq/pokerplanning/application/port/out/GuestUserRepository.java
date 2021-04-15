package org.unq.pokerplanning.application.port.out;

import org.unq.pokerplanning.domain.GuestUser;

import java.util.List;

public interface GuestUserRepository {
    GuestUser getUser(Integer id);

    Integer createGuestUser(GuestUser guestUser);

    List<GuestUser> findByRoom(Integer roomId);
}
