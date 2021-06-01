package org.unq.pokerplanning.adapter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.unq.pokerplanning.adapter.controller.model.RoomPatchRest;
import org.unq.pokerplanning.adapter.controller.model.RoomRest;
import org.unq.pokerplanning.application.port.in.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rooms")
@Slf4j
public final class RoomControllerAdapter {

    private final FindRoomQuery findRoomQuery;
    private final CreateRoomCommand createRoomCommand;
    private final UpdateRoomCommand updateRoomCommand;

    public RoomControllerAdapter(FindRoomQuery findRoomQuery, CreateRoomCommand createRoomCommand, UpdateRoomCommand updateRoomCommand) {
        this.findRoomQuery = findRoomQuery;
        this.createRoomCommand = createRoomCommand;
        this.updateRoomCommand = updateRoomCommand;
    }

    @GetMapping("/{roomId}")
    public Optional<RoomRest> getRoom(@PathVariable Integer roomId) {
        return findRoomQuery.execute(roomId).map(RoomRest::from);
    }

    @PostMapping
    public Integer createRoom(@RequestBody RoomRest roomRest) {
        return createRoomCommand.execute(roomRest.toDomain());
    }

    @PatchMapping("/{roomId}")
    public Integer patchTask(@PathVariable Integer roomId, @RequestBody RoomPatchRest roomPatchRest) {
        return updateRoomCommand.execute(roomPatchRest.toDomain(roomId));
    }
}

