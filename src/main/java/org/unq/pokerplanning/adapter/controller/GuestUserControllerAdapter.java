package org.unq.pokerplanning.adapter.controller;

import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.unq.pokerplanning.adapter.controller.model.GuestUserRest;
import org.unq.pokerplanning.application.port.in.CreateGuestUserCommand;
import org.unq.pokerplanning.application.port.in.FindGuestUserQuery;
import org.unq.pokerplanning.domain.GuestUser;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/guest-users")
@Slf4j
public final class GuestUserControllerAdapter {

    private final CreateGuestUserCommand createGuestUserCommand;
    private final FindGuestUserQuery findGuestUserQuery;

    public GuestUserControllerAdapter(CreateGuestUserCommand createGuestUserCommand, FindGuestUserQuery findGuestUserQuery) {
        this.createGuestUserCommand = createGuestUserCommand;
        this.findGuestUserQuery = findGuestUserQuery;
    }

    @PostMapping()
    public Integer createGuestUser(@RequestBody GuestUserRest guestUserRest) {
        GuestUser guestUser = guestUserRest.toDomain();

        return createGuestUserCommand.execute(guestUser);
    }

    @GetMapping()
    public List<GuestUserRest> findGuestUser(@RequestParam Integer roomId) {
        List<GuestUser> guestUsers = findGuestUserQuery.execute(roomId);
        return guestUsers.stream()
                .map(GuestUserRest::from)
                .collect(Collectors.toList());
    }

}

