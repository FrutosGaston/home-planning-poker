package org.unq.pokerplanning.adapter.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.unq.pokerplanning.adapter.jdbc.exception.SqlResourceException;
import org.unq.pokerplanning.adapter.jdbc.model.GuestUserVO;
import org.unq.pokerplanning.application.port.out.GuestUserRepository;
import org.unq.pokerplanning.config.ErrorCode;
import org.unq.pokerplanning.domain.User;

@Component
@Slf4j
public class GuestUserJDBCAdapter implements GuestUserRepository {

    private static final String CREATE_GUEST_USER_SQL_PATH = "sql/insert-guest-user.sql";
    private final GenericDao genericDAO;
    private final String insertQuery;

    public GuestUserJDBCAdapter(GenericDao genericDAO) {
        this.genericDAO = genericDAO;
        this.insertQuery = SqlReader.get(CREATE_GUEST_USER_SQL_PATH);
    }

    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public Integer createGuestUser(User user, Long roomId) {
        try {
            MapSqlParameterSource map = GuestUserVO.of(user, roomId).toMap();
            return genericDAO.insert(insertQuery, map).intValue();
        } catch (DataAccessException ex) {
            log.error("Ocurrio un error al realizar el insert del usuario: {}, ex: {}", user, ex);
            throw new SqlResourceException(ErrorCode.INSERT_JDBC, ex);
        }
    }

}
