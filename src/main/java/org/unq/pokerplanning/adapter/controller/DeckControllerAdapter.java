package org.unq.pokerplanning.adapter.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unq.pokerplanning.adapter.controller.model.DeckRest;
import org.unq.pokerplanning.application.port.in.FindDecksQuery;
import org.unq.pokerplanning.domain.Deck;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/decks")
@Slf4j
public final class DeckControllerAdapter {

    private final FindDecksQuery findDecksQuery;

    public DeckControllerAdapter(FindDecksQuery findDecksQuery) {
        this.findDecksQuery = findDecksQuery;
    }

    @GetMapping
    public List<DeckRest> findDecks() {
        List<Deck> decks = findDecksQuery.execute();
        return decks.stream().map(DeckRest::from).collect(Collectors.toList());
    }
}