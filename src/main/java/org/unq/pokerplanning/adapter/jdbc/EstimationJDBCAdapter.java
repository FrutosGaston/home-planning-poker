package org.unq.pokerplanning.adapter.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.adapter.jdbc.exception.NotFoundJdbcException;
import org.unq.pokerplanning.adapter.jdbc.model.EstimationVO;
import org.unq.pokerplanning.application.port.out.EstimationRepository;
import org.unq.pokerplanning.application.port.out.RoundRepository;
import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.domain.Estimation;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class EstimationJDBCAdapter implements EstimationRepository {

    private static final String FIND_ESTIMATION_BY_ROUND_SQL_PATH = "sql/find-estimation-by-round.sql";
    private final GenericDao genericDAO;
    private final String findByRoundQuery;

    public EstimationJDBCAdapter(GenericDao genericDAO) {
        this.genericDAO = genericDAO;
        this.findByRoundQuery = SqlReader.get(FIND_ESTIMATION_BY_ROUND_SQL_PATH);
    }

    @Override
    public List<Estimation> findByRound(Integer roundId) {
        try {
            var params = new MapSqlParameterSource()
                    .addValue("round_id", roundId);
            return genericDAO.findObjects(findByRoundQuery, params, EstimationVO.class).stream()
                    .map(EstimationVO::toDomain).collect(Collectors.toList());
        } catch (DataAccessException ex) {
            log.error("Ocurrio un error al buscar la ronda de la base", ex);
            throw new NotFoundJdbcException(ErrorCode.FIND_JDBC, ex);
        }
    }

}
