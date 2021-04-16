package org.unq.pokerplanning.adapter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.unq.pokerplanning.adapter.controller.model.GuestUserRest;
import org.unq.pokerplanning.adapter.controller.model.RoundRest;
import org.unq.pokerplanning.application.port.in.CreateGuestUserCommand;
import org.unq.pokerplanning.application.port.in.FindGuestUserQuery;
import org.unq.pokerplanning.application.port.in.FindRoundQuery;
import org.unq.pokerplanning.domain.GuestUser;
import org.unq.pokerplanning.domain.Round;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/rounds")
@Slf4j
public final class RoundControllerAdapter {

    private final FindRoundQuery findRoundQuery;

    public RoundControllerAdapter(FindRoundQuery findRoundQuery) {
        this.findRoundQuery = findRoundQuery;
    }

    @GetMapping()
    public Optional<RoundRest> getGuestUser(@RequestParam Integer roomId) {
        Optional<Round> roundOptional = findRoundQuery.execute(roomId);
        return roundOptional.map(RoundRest::from);
    }

}

