package org.unq.pokerplanning.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class Deck {
    Integer id;
    String name;
    @With List<Card> cards;
    LocalDateTime createdAt;
}
