package ru.system.monitoring.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.system.library.sql.queries.ReferenceSQLQueries;
import ru.system.library.sql.repository.ReferenceRepositoryInterface;

import java.util.Map;
import java.util.UUID;

@Component
public class ReferenceRepository extends ReferenceRepositoryInterface {

    public ReferenceRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    public void changeReference(UUID id, Double newValue) {
        namedParameterJdbcTemplate.update(
                ReferenceSQLQueries.UPDATE_VALUE,
                Map.of("id", id, "value", newValue)
        );
    }
}
