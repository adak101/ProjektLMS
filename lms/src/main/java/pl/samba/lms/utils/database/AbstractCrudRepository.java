package pl.samba.lms.utils.database;

import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstrakcyjna klasa służąca do dostarczenia podstawowej implementacji operacji CRUD
 *  dla repozytorium .
 *
 * @param <T> Typ obiektów zarządzanych przez repozytorium.
 * @param <K> Typ unikalnego identyfikatora dla obiektów.
 * @author bsurma
 */
public abstract class AbstractCrudRepository<T, K> implements CrudRepositoryInterface<T, K>{

    @Getter
    private final static String SCHEMA = "lms";
    @Getter
    private final String tableName;
    @Getter
    private final String pkColumnName;
    @Getter
    private final String readProcName;
    @Getter
    private final String insertProcName;
    @Getter
    private final String updateProcName;
    @Getter
    private final String deleteProcName;

    @Getter
    private final JdbcTemplate jdbc;

    public AbstractCrudRepository(
            JdbcTemplate jdbc,
            String tableName,
            String pkColumnNameName
    ){
        this.jdbc = jdbc;
        this.tableName = tableName;
        this.pkColumnName = pkColumnNameName;

        readProcName = tableName + "_read";
        insertProcName = tableName + "_ins";
        updateProcName = tableName + "_upd";
        deleteProcName = tableName + "_del";
    }
    @Override
    public Iterable<T> getAll() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc)
                .withSchemaName(getSCHEMA())
                .withProcedureName(readProcName);
        Map<String, Object> inParams = new HashMap<>();
        inParams.put(pkColumnName, null);

        Map<String, Object> result = jdbcCall.execute(inParams);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.get("#result-set-1");

        return resultMapper(resultSet);
    }

    @Override
    public Iterable<T> getAll(String requestParams) {
        //TODO
        return null;
    }

    @Override
    public T getById(K id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc)
                .withSchemaName(getSCHEMA())
                .withProcedureName(readProcName);
        Map<String, Object> inParams = new HashMap<>();
        inParams.put(pkColumnName, id);

        Map<String, Object> result = jdbcCall.execute(inParams);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.get("#result-set-1");

        return resultMapper(resultSet).iterator().hasNext() ? resultMapper(resultSet).iterator().next() : null ;
    }

    @Override
    public boolean delete(K id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc)
                .withSchemaName(getSCHEMA())
                .withProcedureName(deleteProcName);
        Map<String, Object> inParams = new HashMap<>();
        inParams.put(pkColumnName, id);

        jdbcCall.execute(inParams);
        return false;
    }


}
