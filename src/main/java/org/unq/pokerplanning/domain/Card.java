package org.unq.pokerplanning.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class Card {
    Integer id;
    String value;
    Integer deckId;
    LocalDateTime createdAt;

}
