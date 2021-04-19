package org.unq.pokerplanning.adapter.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.adapter.jdbc.exception.NotFoundJdbcException;
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
    private final GenericDao genericDAO;
    private final String findByRoomQuery;

    public TaskJDBCAdapter(GenericDao genericDAO) {
        this.genericDAO = genericDAO;
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

}
