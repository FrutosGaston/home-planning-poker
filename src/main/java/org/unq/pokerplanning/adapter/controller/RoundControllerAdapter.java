package org.unq.pokerplanning.adapter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.unq.pokerplanning.adapter.controller.model.EstimationRest;
import org.unq.pokerplanning.adapter.controller.model.RoundRest;
import org.unq.pokerplanning.application.port.in.CreateEstimationCommand;
import org.unq.pokerplanning.application.port.in.FindRoundQuery;
import org.unq.pokerplanning.domain.Round;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rounds")
@Slf4j
public final class RoundControllerAdapter {

    private final FindRoundQuery findRoundQuery;
    private final CreateEstimationCommand createEstimationCommand;

    public RoundControllerAdapter(FindRoundQuery findRoundQuery, CreateEstimationCommand createEstimationCommand) {
        this.findRoundQuery = findRoundQuery;
        this.createEstimationCommand = createEstimationCommand;
    }

    @GetMapping()
    public Optional<RoundRest> getGuestUser(@RequestParam Integer roomId) {
        Optional<Round> roundOptional = findRoundQuery.execute(roomId);
        return roundOptional.map(RoundRest::from);
    }

    @PostMapping("/estimations")
    public Integer getGuestUser(@RequestBody EstimationRest estimation) {
        return createEstimationCommand.execute(estimation.toDomain());

    }
}

