package org.unq.pokerplanning.adapter.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.adapter.jdbc.exception.NotFoundJdbcException;
import org.unq.pokerplanning.adapter.jdbc.model.DeckVO;
import org.unq.pokerplanning.application.port.out.DeckRepository;
import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.domain.Deck;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DeckJDBCAdapter implements DeckRepository {
    private static final String FIND_DECKS_SQL_PATH = "sql/find-decks.sql";
    private final GenericDao genericDAO;
    private final String findQuery;

    public DeckJDBCAdapter(GenericDao genericDAO) {
        this.genericDAO = genericDAO;
        this.findQuery = SqlReader.get(FIND_DECKS_SQL_PATH);
    }

    @Override
    public List<Deck> find() {
        try {
            var params = new MapSqlParameterSource();
            return genericDAO.findObjects(findQuery, params, DeckVO.class).stream()
                    .map(DeckVO::toDomain).collect(Collectors.toList());
        } catch (DataAccessException ex) {
            log.error("Ocurrio un error al buscar los mazos de la base", ex);
            throw new NotFoundJdbcException(ErrorCode.FIND_JDBC, ex);
        }
    }
}
