package org.unq.pokerplanning.adapter.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.adapter.jdbc.exception.NotFoundJdbcException;
import org.unq.pokerplanning.adapter.jdbc.exception.SqlResourceException;
import org.unq.pokerplanning.adapter.jdbc.model.GuestUserVO;
import org.unq.pokerplanning.application.port.out.GuestUserRepository;
import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.domain.GuestUser;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GuestUserJDBCAdapter implements GuestUserRepository {

    private static final String CREATE_GUEST_USER_SQL_PATH = "sql/insert-guest-user.sql";
    private static final String FIND_GUEST_USER_BY_ROOM_SQL_PATH = "sql/find-guest-users-by-room.sql";
    private final GenericDao genericDAO;
    private final String insertQuery;
    private final String findByRoomQuery;

    public GuestUserJDBCAdapter(GenericDao genericDAO) {
        this.genericDAO = genericDAO;
        this.insertQuery = SqlReader.get(CREATE_GUEST_USER_SQL_PATH);
        this.findByRoomQuery = SqlReader.get(FIND_GUEST_USER_BY_ROOM_SQL_PATH);
    }

    @Override
    public GuestUser getUser(Integer id) {
        return null;
    }

    @Override
    public Integer createGuestUser(GuestUser guestUser) {
        try {
            MapSqlParameterSource map = GuestUserVO.of(guestUser).toMap();
            String[] keys = {"id"};
            return genericDAO.insert(insertQuery, map, keys).intValue();
        } catch (DataAccessException ex) {
            log.error("Ocurrio un error al realizar el insert del usuario: {}, ex: {}", guestUser, ex);
            throw new SqlResourceException(ErrorCode.INSERT_JDBC, ex);
        }
    }

    @Override
    public List<GuestUser> findByRoom(Integer roomId) {
        try {
            var params = new MapSqlParameterSource()
                    .addValue("room_id", roomId);
            return genericDAO.findObjects(findByRoomQuery, params, GuestUserVO.class).stream()
                    .map(GuestUserVO::toDomain).collect(Collectors.toList());
        } catch (DataAccessException ex) {
            log.error("Ocurrio un error al buscar usuarios invitados de la base", ex);
            throw new NotFoundJdbcException(ErrorCode.FIND_JDBC, ex);
        }
    }

}
