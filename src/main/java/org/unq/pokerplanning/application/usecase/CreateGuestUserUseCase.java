package org.unq.pokerplanning.application.usecase;

import org.unq.pokerplanning.application.port.in.CreateGuestUserCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.out.GuestUserRepository;
import org.unq.pokerplanning.domain.GuestUser;

@Component
@Slf4j
public class CreateGuestUserUseCase implements CreateGuestUserCommand {

    private final GuestUserRepository guestUserRepository;

    public CreateGuestUserUseCase(GuestUserRepository guestUserRepository) {
        this.guestUserRepository = guestUserRepository;
    }

    @Override
    public GuestUser execute(GuestUser guestUser) {
        guestUserRepository.createGuestUser(guestUser);
        return guestUser;
    }
}
