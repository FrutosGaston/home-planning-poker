package org.unq.pokerplanning.adapter.controller.model;

import lombok.Builder;
import lombok.Value;
import org.unq.pokerplanning.domain.Card;
import org.unq.pokerplanning.domain.Estimation;

import java.time.LocalDateTime;

@Value
@Builder
public class CardRest {
    Integer id;
    Integer deckId;
    String value;
    LocalDateTime createdAt;

    public static CardRest from(Card card) {
        return CardRest.builder()
                .id(card.getId())
                .deckId(card.getDeckId())
                .value(card.getValue())
                .createdAt(card.getCreatedAt())
                .build();
    }
}
