package org.unq.pokerplanning.adapter.jdbc;

import lombok.experimental.UtilityClass;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.unq.pokerplanning.adapter.jdbc.exception.SqlResourceException;

import java.io.InputStreamReader;
import java.io.Reader;

import static java.nio.charset.StandardCharsets.UTF_8;

@UtilityClass
public class SqlReader {

    public String get(String res) {
        try {
            DefaultResourceLoader loader = new DefaultResourceLoader();
            Resource resource = loader.getResource(res);
            Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8.name());
            return FileCopyUtils.copyToString(reader);
        } catch (Exception e) {
            String errorMessage = String.format("Error cargando recurso SQL %s", res);
            throw new SqlResourceException(errorMessage, e);
        }
    }
}
