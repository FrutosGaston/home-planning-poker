package org.unq.pokerplanning.adapter.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.adapter.jdbc.exception.NotFoundJdbcException;
import org.unq.pokerplanning.adapter.jdbc.model.CardVO;
import org.unq.pokerplanning.application.port.out.CardRepository;
import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.domain.Card;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CardJDBCAdapter implements CardRepository {

    private static final String GET_CARD_SQL_PATH = "sql/get-card.sql";
    private static final String FIND_CARD_BY_DECK_SQL_PATH = "sql/find-cards-by-deck.sql";
    private final GenericDao genericDAO;
    private final String getQuery;
    private final String findByDeckQuery;

    public CardJDBCAdapter(GenericDao genericDAO) {
        this.genericDAO = genericDAO;
        this.getQuery = SqlReader.get(GET_CARD_SQL_PATH);
        this.findByDeckQuery = SqlReader.get(FIND_CARD_BY_DECK_SQL_PATH);
    }

    @Override
    public Optional<Card> get(Integer cardId) {
        try {
            var params = new MapSqlParameterSource()
                    .addValue("id", cardId);
            return genericDAO.findObject(getQuery, params, CardVO.class).map(CardVO::toDomain);
        } catch (DataAccessException ex) {
            log.error("Ocurrio un error al buscar la carta de la base", ex);
            throw new NotFoundJdbcException(ErrorCode.FIND_JDBC, ex);
        }
    }

    @Override
    public List<Card> findByDeck(Integer deckId) {
        try {
            var params = new MapSqlParameterSource()
                    .addValue("deck_id", deckId);
            return genericDAO.findObjects(findByDeckQuery, params, CardVO.class).stream()
                    .map(CardVO::toDomain).collect(Collectors.toList());
        } catch (DataAccessException ex) {
            log.error("Ocurrio un error al buscar las cartas de la base", ex);
            throw new NotFoundJdbcException(ErrorCode.FIND_JDBC, ex);
        }
    }

}
