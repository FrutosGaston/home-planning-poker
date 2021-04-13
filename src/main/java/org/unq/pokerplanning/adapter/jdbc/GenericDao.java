package org.unq.pokerplanning.adapter.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class GenericDao {

    private static final String MENSAJE = "Se va a ejecutar SQL {} con params {}";

    private final NamedParameterJdbcTemplate template;

    @Autowired
    public GenericDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public <T> Optional<T> findObject(String sql, SqlParameterSource params, Class<T> clazz) {
        return doFindObjects(sql, params, clazz).stream().findFirst();
    }

    public Number insert(String sql, MapSqlParameterSource params) {
        log.debug(MENSAJE, sql, params);
        return template.update(sql, params);
    }

    private <T> List<T> doFindObjects(String sql, SqlParameterSource params, Class<T> clazz) {
        log.debug(MENSAJE, sql, params);
        return template.query(sql, params, new BeanPropertyRowMapper<>(clazz));
    }

    public Integer updateObject(String sql, SqlParameterSource params) {
        log.debug(MENSAJE, sql, params);
        return template.update(sql, params);
    }
}
