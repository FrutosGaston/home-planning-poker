package org.unq.pokerplanning.application.usecase;

import lombok.val;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.application.port.in.FindDecksQuery;
import org.unq.pokerplanning.application.port.out.CardRepository;
import org.unq.pokerplanning.application.port.out.DeckRepository;
import org.unq.pokerplanning.domain.Deck;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindDecksUseCase implements FindDecksQuery {

    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;

    public FindDecksUseCase(DeckRepository deckRepository, CardRepository cardRepository) {
        this.deckRepository = deckRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Deck> execute() {
        val decks = this.deckRepository.find();
        return decks.stream().map(deck -> {
            val cards = this.cardRepository.findByDeck(deck.getId());
            return deck.withCards(cards);
        }).collect(Collectors.toList());
    }

}
