package org.unq.pokerplanning.application.port.in;

import org.unq.pokerplanning.domain.Deck;

import java.util.List;

public interface FindDecksQuery {
    List<Deck> execute();
}
