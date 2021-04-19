package org.unq.pokerplanning.adapter.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.adapter.jdbc.exception.NotFoundJdbcException;
import org.unq.pokerplanning.adapter.jdbc.exception.SqlResourceException;
import org.unq.pokerplanning.adapter.jdbc.model.EstimationVO;
import org.unq.pokerplanning.application.port.out.EstimationRepository;
import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.domain.Estimation;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class EstimationJDBCAdapter implements EstimationRepository {

    private static final String FIND_ESTIMATION_BY_TASK_SQL_PATH = "sql/find-estimation-by-task.sql";
    private static final String INSERT_ESTIMATION_SQL_PATH = "sql/insert-estimation.sql";
    private final GenericDao genericDAO;
    private final String findByTaskQuery;
    private final String insertQuery;

    public EstimationJDBCAdapter(GenericDao genericDAO) {
        this.genericDAO = genericDAO;
        this.findByTaskQuery = SqlReader.get(FIND_ESTIMATION_BY_TASK_SQL_PATH);
        this.insertQuery = SqlReader.get(INSERT_ESTIMATION_SQL_PATH);
    }

    @Override
    public List<Estimation> findByTask(Integer taskId) {
        try {
            var params = new MapSqlParameterSource()
                    .addValue("task_id", taskId);
            return genericDAO.findObjects(findByTaskQuery, params, EstimationVO.class).stream()
                    .map(EstimationVO::toDomain).collect(Collectors.toList());
        } catch (DataAccessException ex) {
            log.error("Ocurrio un error al buscar la ronda de la base", ex);
            throw new NotFoundJdbcException(ErrorCode.FIND_JDBC, ex);
        }
    }

    @Override
    public Integer create(Estimation estimation) {
        try {
            MapSqlParameterSource map = EstimationVO.of(estimation).toMap();
            String[] keys = {"id"};
            return genericDAO.insert(insertQuery, map, keys).intValue();
        } catch (DataAccessException ex) {
            log.error("Ocurrio un error al realizar el insert de la estimacion: {}, ex: {}", estimation, ex);
            throw new SqlResourceException(ErrorCode.INSERT_JDBC, ex);
        }
    }

}
