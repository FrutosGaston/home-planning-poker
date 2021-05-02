package org.unq.pokerplanning.application.usecase;

import lombok.val;
import org.unq.pokerplanning.application.port.in.CreateGuestUserCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.out.GuestUserMessenger;
import org.unq.pokerplanning.application.port.out.GuestUserRepository;
import org.unq.pokerplanning.domain.GuestUser;

@Component
@Slf4j
public class CreateGuestUserUseCase implements CreateGuestUserCommand {

    private final GuestUserRepository guestUserRepository;
    private final GuestUserMessenger guestUserMessenger;

    public CreateGuestUserUseCase(GuestUserRepository guestUserRepository, GuestUserMessenger guestUserMessenger) {
        this.guestUserRepository = guestUserRepository;
        this.guestUserMessenger = guestUserMessenger;
    }

    @Override
    public Integer execute(GuestUser guestUser) {
        val guestUserId = guestUserRepository.createGuestUser(guestUser);
        val guestUserWithId = guestUser.withId(guestUserId);
        guestUserMessenger.created(guestUser.getRoomId(), guestUserWithId);
        return guestUserId;
    }
}
