package org.unq.pokerplanning.application.usecase;

import org.unq.pokerplanning.application.port.in.AddUserToRoomCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.out.GuestUserRepository;
import org.unq.pokerplanning.domain.User;

@Component
@Slf4j
public class AddUserToRoomUseCase implements AddUserToRoomCommand {

    private final GuestUserRepository guestUserRepository;

    public AddUserToRoomUseCase(GuestUserRepository guestUserRepository) {
        this.guestUserRepository = guestUserRepository;
    }

    @Override
    public User execute(User user, Long roomId) {
        guestUserRepository.createGuestUser(user, roomId);
        return user;
    }
}
