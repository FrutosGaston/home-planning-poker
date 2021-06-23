package org.unq.pokerplanning.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Value
public class Room {
    Integer id;
    @With UUID uuid;
    Integer deckId;
    Integer selectedTaskId;
    String title;
    String description;
    LocalDateTime createdAt;
}
