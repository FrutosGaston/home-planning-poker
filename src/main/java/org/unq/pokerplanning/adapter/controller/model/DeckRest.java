package org.unq.pokerplanning.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import lombok.val;
import org.unq.pokerplanning.domain.Deck;
import org.unq.pokerplanning.domain.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Value
@Builder
public class DeckRest {
    Integer id;
    String name;
    List<CardRest> cards;
    LocalDateTime createdAt;

    public static DeckRest from(Deck deck) {
        return DeckRest.builder()
                .id(deck.getId())
                .name(deck.getName())
                .cards(deck.getCards().stream()
                        .map(CardRest::from)
                        .collect(Collectors.toList()))
                .createdAt(deck.getCreatedAt())
                .build();
    }

}
