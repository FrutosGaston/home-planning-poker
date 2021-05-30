package org.unq.pokerplanning.adapter.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.adapter.jdbc.exception.NotFoundJdbcException;
import org.unq.pokerplanning.adapter.jdbc.exception.SqlResourceException;
import org.unq.pokerplanning.adapter.jdbc.model.EstimationVO;
import org.unq.pokerplanning.adapter.jdbc.model.TaskVO;
import org.unq.pokerplanning.application.port.out.EstimationRepository;
import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.domain.Estimation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class EstimationJDBCAdapter implements EstimationRepository {

    private static final String FIND_ESTIMATION_BY_TASK_SQL_PATH = "sql/find-estimation-by-task.sql";
    private static final String GET_ESTIMATION_SQL_PATH = "sql/get-estimation.sql";
    private static final String INSERT_ESTIMATION_SQL_PATH = "sql/insert-estimation.sql";
    private static final String INVALIDATE_ESTIMATIONS_SQL_PATH = "sql/invalidate-estimations.sql";
    private final GenericDao genericDAO;
    private final String findByTaskQuery;
    private final String getEstimationQuery;
    private final String insertQuery;
    private final String invalidateAllQuery;

    public EstimationJDBCAdapter(GenericDao genericDAO) {
        this.genericDAO = genericDAO;
        this.findByTaskQuery = SqlReader.get(FIND_ESTIMATION_BY_TASK_SQL_PATH);
        this.getEstimationQuery = SqlReader.get(GET_ESTIMATION_SQL_PATH);
        this.insertQuery = SqlReader.get(INSERT_ESTIMATION_SQL_PATH);
        this.invalidateAllQuery = SqlReader.get(INVALIDATE_ESTIMATIONS_SQL_PATH);
    }

    @Override
    public List<Estimation> findByTask(Integer taskId) {
        try {
            var params = new MapSqlParameterSource()
                    .addValue("task_id", taskId);
            return genericDAO.findObjects(findByTaskQuery, params, EstimationVO.class).stream()
                    .map(EstimationVO::toDomain).collect(Collectors.toList());
        } catch (DataAccessException ex) {
            log.error("Ocurrio un error al buscar las estimaciones de la base", ex);
            throw new NotFoundJdbcException(ErrorCode.FIND_JDBC, ex);
        }
    }

    @Override
    public Optional<Estimation> get(Integer estimationId) {
        try {
            var params = new MapSqlParameterSource()
                    .addValue("id", estimationId);
            return genericDAO.findObject(getEstimationQuery, params, EstimationVO.class)
                    .map(EstimationVO::toDomain);
        } catch (DataAccessException ex) {
            log.error("Ocurrio un error al buscar la estimacion de la base", ex);
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

    @Override
    public Integer invalidateAll(Integer taskId) {
        try {
            MapSqlParameterSource map = EstimationVO.builder().taskId(taskId).build()
                    .toInvalidateMap();
            return genericDAO.updateObject(invalidateAllQuery, map);
        } catch (DataAccessException ex) {
            log.error("Ocurrio un error al invalidar las estimaciones de la tarea: {}, ex: {}", taskId, ex);
            throw new SqlResourceException(ErrorCode.INSERT_JDBC, ex);
        }
    }

}
