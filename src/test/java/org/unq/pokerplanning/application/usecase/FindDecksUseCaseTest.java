package org.unq.pokerplanning.application.usecase;

import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.unq.pokerplanning.application.port.out.CardRepository;
import org.unq.pokerplanning.application.port.out.DeckRepository;
import org.unq.pokerplanning.domain.Card;
import org.unq.pokerplanning.domain.Deck;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("findTaskUseCase Test")
@ExtendWith(MockitoExtension.class)
class FindDecksUseCaseTest {

    private static final int CORE_POOL_SIZE = 20;
    private static final int MAX_POOL_SIZE = 1000;
    private static final String ASYNC_PREFIX = "async-";
    private static final boolean WAIT_FOR_TASK_TO_COMPLETE_ON_SHUTDOWN = true;

    private final DeckRepository deckRepository = mock(DeckRepository.class);
    private final CardRepository cardRepository = mock(CardRepository.class);

    @BeforeAll
    static void init() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setWaitForTasksToCompleteOnShutdown(WAIT_FOR_TASK_TO_COMPLETE_ON_SHUTDOWN);
        executor.setThreadNamePrefix(ASYNC_PREFIX);
        executor.initialize();
    }

    @Test
    @DisplayName("When findDecks is executed Should Return a list of decks with its cards")
    void findTaskOk() {

        //given
        Integer deckId = 1;
        Deck deck = Deck.builder().id(deckId).name("fibonacci").build();
        Card card = Card.builder().id(1).value("1").deckId(deckId).build();

        when(cardRepository.findByDeck(deckId)).thenReturn(List.of(card));
        when(deckRepository.find()).thenReturn(List.of(deck));

        FindDecksUseCase findDecksUseCase = new FindDecksUseCase(deckRepository, cardRepository);

        //when
        Deck result = findDecksUseCase.execute().get(0);

        //then
        assertEquals(deck.getId(), result.getId());
        assertEquals(card, result.getCards().get(0));
    }

}
