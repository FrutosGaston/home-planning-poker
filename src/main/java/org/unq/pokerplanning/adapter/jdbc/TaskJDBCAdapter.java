package org.unq.pokerplanning.adapter.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.adapter.jdbc.exception.NotFoundJdbcException;
import org.unq.pokerplanning.adapter.jdbc.exception.SqlResourceException;
import org.unq.pokerplanning.adapter.jdbc.model.TaskVO;
import org.unq.pokerplanning.application.port.out.TaskRepository;
import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.domain.Task;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TaskJDBCAdapter implements TaskRepository {

    private static final String FIND_TASK_BY_ROOM_SQL_PATH = "sql/find-task-by-room.sql";
    private static final String UPDATE_TASK_SQL_PATH = "sql/update-task.sql";
    private final GenericDao genericDAO;
    private final String findByRoomQuery;
    private final String updateQuery;

    public TaskJDBCAdapter(GenericDao genericDAO) {
        this.genericDAO = genericDAO;
        this.updateQuery = SqlReader.get(UPDATE_TASK_SQL_PATH);
        this.findByRoomQuery = SqlReader.get(FIND_TASK_BY_ROOM_SQL_PATH);
    }

    @Override
    public List<Task> findByRoom(Integer roomId) {
        try {
            var params = new MapSqlParameterSource()
                    .addValue("room_id", roomId);
            return genericDAO.findObjects(findByRoomQuery, params, TaskVO.class).stream()
                    .map(TaskVO::toDomain).collect(Collectors.toList());
        } catch (DataAccessException ex) {
            log.error("Ocurrio un error al buscar la ronda de la base", ex);
            throw new NotFoundJdbcException(ErrorCode.FIND_JDBC, ex);
        }
    }

    @Override
    public Integer update(Task task) {
        try {
            MapSqlParameterSource map = TaskVO.of(task).toMap();
            return genericDAO.updateObject(updateQuery, map);
        } catch (DataAccessException ex) {
            log.error("Ocurrio un error al realizar el update de la estimacion: {}, ex: {}", task, ex);
            throw new SqlResourceException(ErrorCode.INSERT_JDBC, ex);
        }
    }

}
