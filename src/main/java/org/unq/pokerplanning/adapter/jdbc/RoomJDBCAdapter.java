package org.unq.pokerplanning.adapter.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.adapter.jdbc.exception.NotFoundJdbcException;
import org.unq.pokerplanning.adapter.jdbc.exception.SqlResourceException;
import org.unq.pokerplanning.adapter.jdbc.model.GuestUserVO;
import org.unq.pokerplanning.adapter.jdbc.model.RoomVO;
import org.unq.pokerplanning.adapter.jdbc.model.TaskVO;
import org.unq.pokerplanning.application.port.out.GuestUserRepository;
import org.unq.pokerplanning.application.port.out.RoomRepository;
import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.domain.GuestUser;
import org.unq.pokerplanning.domain.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RoomJDBCAdapter implements RoomRepository {

    private static final String CREATE_ROOM_SQL_PATH = "sql/insert-room.sql";
    private static final String UPDATE_ROOM_SQL_PATH = "sql/update-room.sql";
    private static final String GET_ROOM_SQL_PATH = "sql/get-room.sql";
    private static final String GET_ROOM_BY_UUID_SQL_PATH = "sql/get-room-by-uuid.sql";
    private final GenericDao genericDAO;
    private final String insertQuery;
    private final String updateQuery;
    private final String getQuery;
    private final String getByUUIDQuery;

    public RoomJDBCAdapter(GenericDao genericDAO) {
        this.genericDAO = genericDAO;
        this.insertQuery = SqlReader.get(CREATE_ROOM_SQL_PATH);
        this.updateQuery = SqlReader.get(UPDATE_ROOM_SQL_PATH);
        this.getQuery = SqlReader.get(GET_ROOM_SQL_PATH);
        this.getByUUIDQuery = SqlReader.get(GET_ROOM_BY_UUID_SQL_PATH);
    }

    @Override
    public Integer create(Room room) {
        try {
            MapSqlParameterSource map = RoomVO.of(room).toMap();
            String[] keys = {"id"};
            return genericDAO.insert(insertQuery, map, keys).intValue();
        } catch (DataAccessException ex) {
            log.error("Ocurrio un error al realizar el insert de la sala: {}, ex: {}", room, ex);
            throw new SqlResourceException(ErrorCode.INSERT_JDBC, ex);
        }
    }

    @Override
    public Optional<Room> getById(Integer roomId) {
        try {
            var params = new MapSqlParameterSource()
                    .addValue("id", roomId);
            return genericDAO.findObject(getQuery, params, RoomVO.class).map(RoomVO::toDomain);
        } catch (DataAccessException ex) {
            log.error("Ocurrio un error al buscar la sala de la base", ex);
            throw new NotFoundJdbcException(ErrorCode.FIND_JDBC, ex);
        }
    }

    @Override
    public Optional<Room> getByUUID(UUID uuid) {
        try {
            var params = new MapSqlParameterSource()
                    .addValue("uuid", uuid);
            return genericDAO.findObject(getByUUIDQuery, params, RoomVO.class).map(RoomVO::toDomain);
        } catch (DataAccessException ex) {
            log.error("Ocurrio un error al buscar la sala de la base", ex);
            throw new NotFoundJdbcException(ErrorCode.FIND_JDBC, ex);
        }
    }

    @Override
    public Integer update(Room room) {
        try {
            MapSqlParameterSource map = RoomVO.of(room).toUpdateMap();
            return genericDAO.updateObject(updateQuery, map);
        } catch (DataAccessException ex) {
            log.error("Ocurrio un error al realizar el update de la sala: {}, ex: {}", room, ex);
            throw new SqlResourceException(ErrorCode.INSERT_JDBC, ex);
        }    }
}
