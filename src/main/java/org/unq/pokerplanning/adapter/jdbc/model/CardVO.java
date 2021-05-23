package org.unq.pokerplanning.adapter.jdbc.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unq.pokerplanning.domain.Card;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CardVO {
    private Integer id;
    private Integer deckId;
    private String value;
    private LocalDateTime createdAt;

    public static CardVO of(Card card) {
        return CardVO.builder()
                .id(card.getId())
                .value(card.getValue())
                .deckId(card.getDeckId())
                .createdAt(card.getCreatedAt())
                .build();
    }

    public Card toDomain() {
        return Card.builder()
                .id(this.id)
                .value(this.value)
                .deckId(this.deckId)
                .createdAt(this.createdAt)
                .build();
    }
}