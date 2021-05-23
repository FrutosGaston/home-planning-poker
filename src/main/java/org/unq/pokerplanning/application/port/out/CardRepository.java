package org.unq.pokerplanning.application.port.out;

import org.unq.pokerplanning.domain.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository {
    Optional<Card> get(Integer cardId);
    List<Card> findByDeck(Integer deckId);
}
