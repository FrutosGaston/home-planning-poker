package org.unq.pokerplanning.application.port.in;

import org.unq.pokerplanning.domain.User;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

public interface AddUserToRoomCommand {
    User execute(User user, Long roomId);
}
