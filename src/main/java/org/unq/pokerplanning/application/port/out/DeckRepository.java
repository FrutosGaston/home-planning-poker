package org.unq.pokerplanning.application.port.out;

import org.unq.pokerplanning.domain.Deck;

import java.util.List;

public interface DeckRepository {
    List<Deck> find();
}
