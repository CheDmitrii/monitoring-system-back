package ru.system.monitoring.repository.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import ru.system.library.dto.common.JournalEntityDTO;
import ru.system.library.sql.repository.mapper.JournalEntityMapperCut;
import ru.system.library.sql.repository.mapper.JournalEntityMapper;
import ru.system.library.sql.queries.JournalSQLQueries;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JournalRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JournalEntityMapper journalEntityMapper;
    private final JournalEntityMapperCut journalEntityMapperCut;

    public void writeJournal(JournalEntityDTO journalEntityDTO) {
        namedParameterJdbcTemplate.update(
                JournalSQLQueries.WRITE_JOURNAL,
                Map.of(
                        "sensor_id", journalEntityDTO.getId(),
                        "value", journalEntityDTO.getValue(),
                        "time", journalEntityDTO.getTime()
                )
        );
    }

    public List<JournalEntityDTO> getAllJournals(UUID sensor_id) {
        List<JournalEntityDTO> result = namedParameterJdbcTemplate.query(
                JournalSQLQueries.GET_JOURNALS_BY_ID,
                Map.of("sensor_id", sensor_id),
                journalEntityMapperCut
        );
        result.sort(Comparator.comparing(JournalEntityDTO::getTime));
        return result;
    }

    public List<JournalEntityDTO> getAllJournals() {
        return namedParameterJdbcTemplate.query(
                JournalSQLQueries.GET_ALL_JOURNALS,
                journalEntityMapper
        );
    }
}
