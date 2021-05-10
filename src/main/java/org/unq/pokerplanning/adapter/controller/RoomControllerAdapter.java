package org.unq.pokerplanning.adapter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.unq.pokerplanning.adapter.controller.model.RoomRest;
import org.unq.pokerplanning.application.port.in.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rooms")
@Slf4j
public final class RoomControllerAdapter {

    private final FindRoomQuery findRoomQuery;
    private final CreateRoomCommand createRoomCommand;

    public RoomControllerAdapter(FindRoomQuery findRoomQuery, CreateRoomCommand createRoomCommand) {
        this.findRoomQuery = findRoomQuery;
        this.createRoomCommand = createRoomCommand;
    }

    @GetMapping("/{roomId}")
    public Optional<RoomRest> getRoom(@PathVariable Integer roomId) {
        return findRoomQuery.execute(roomId).map(RoomRest::from);
    }

    @PostMapping
    public Integer createRoom(@RequestBody RoomRest roomRest) {
        return createRoomCommand.execute(roomRest.toDomain());
    }
}

