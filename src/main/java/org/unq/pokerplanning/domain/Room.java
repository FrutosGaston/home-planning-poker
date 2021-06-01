package org.unq.pokerplanning.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Builder
@Value
public class Room {
    Integer id;
    Integer deckId;
    Integer selectedTaskId;
    String title;
    String description;
    LocalDateTime createdAt;
}
