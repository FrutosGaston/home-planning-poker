package org.unq.pokerplanning.application.port.in;

import org.unq.pokerplanning.domain.GuestUser;

import java.util.List;

public interface FindGuestUserQuery {
    List<GuestUser> execute(Integer roomId);
}
