package org.unq.pokerplanning.adapter.controller;


import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.unq.pokerplanning.adapter.controller.model.GuestUserRest;
import org.unq.pokerplanning.application.port.in.CreateGuestUserCommand;
import org.unq.pokerplanning.domain.GuestUser;

@RestController
@RequestMapping("/api/v1/guest-users")
@Slf4j
public final class GuestUserControllerAdapter {

    private final CreateGuestUserCommand createGuestUserCommand;

    public GuestUserControllerAdapter(CreateGuestUserCommand createGuestUserCommand) {
        this.createGuestUserCommand = createGuestUserCommand;
    }

    @PostMapping()
    public GuestUserRest addUserToRoom(@RequestBody GuestUserRest guestUserRest) {
        GuestUser guestUser = guestUserRest.toDomain();

        return GuestUserRest.from(createGuestUserCommand.execute(guestUser));
    }

}

