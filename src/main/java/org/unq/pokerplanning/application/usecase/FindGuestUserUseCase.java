package org.unq.pokerplanning.application.usecase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.in.FindGuestUserQuery;
import org.unq.pokerplanning.application.port.out.GuestUserRepository;
import org.unq.pokerplanning.domain.GuestUser;

import java.util.List;

@Component
@Slf4j
public class FindGuestUserUseCase implements FindGuestUserQuery {

    private final GuestUserRepository guestUserRepository;

    public FindGuestUserUseCase(GuestUserRepository guestUserRepository) {
        this.guestUserRepository = guestUserRepository;
    }

    @Override
    public List<GuestUser> execute(Integer roomId) {
        return guestUserRepository.findByRoom(roomId);
    }
}
