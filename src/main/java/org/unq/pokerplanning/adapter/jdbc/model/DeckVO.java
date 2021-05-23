package org.unq.pokerplanning.adapter.jdbc.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unq.pokerplanning.domain.Card;
import org.unq.pokerplanning.domain.Deck;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DeckVO {
    private Integer id;
    private String name;
    private LocalDateTime createdAt;

    public Deck toDomain() {
        return Deck.builder()
                .id(this.id)
                .name(this.name)
                .createdAt(this.createdAt)
                .build();
    }
}