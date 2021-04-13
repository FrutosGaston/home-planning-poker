package org.unq.pokerplanning.adapter.controller;


import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.unq.pokerplanning.adapter.controller.model.UserRest;
import org.unq.pokerplanning.application.port.in.AddUserToRoomCommand;
import org.unq.pokerplanning.domain.User;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/rooms")
@Slf4j
public final class RoomControllerAdapter {

    private final AddUserToRoomCommand addUserToRoomCommand;

    public RoomControllerAdapter(AddUserToRoomCommand addUserToRoomCommand) {
        this.addUserToRoomCommand = addUserToRoomCommand;
    }

    @PostMapping("/{roomId}/users")
    public UserRest addUserToRoom(@RequestBody UserRest userRest, @PathVariable Long roomId) {
        User user = userRest.toUser();

        return UserRest.from(addUserToRoomCommand.execute(user, roomId));
    }

}

