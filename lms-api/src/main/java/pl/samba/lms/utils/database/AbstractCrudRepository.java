package pl.samba.lms.utils.database;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final static String P_PAGE_SIZE = "p_size";
    private final static String P_PAGE = "p_page";
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
    @Autowired
    private JdbcTemplate jdbc;

    public AbstractCrudRepository(
            String tableName,
            String pkColumnNameName
    ){
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
        inParams.put(P_PAGE_SIZE, null);
        inParams.put(P_PAGE, null);

        Map<String, Object> result = jdbcCall.execute(inParams);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.get("#result-set-1");

        return resultMapper(resultSet);
    }

    @Override
    public Iterable<T> getAll(String requestParams) {
        /*
         * requestParamsTable
         * [0] size
         * [1] page
         * */
        String[] requestParamsTable = requestParams.split(";");
        int size = Integer.parseInt(requestParamsTable[0]);
        int page = Integer.parseInt(requestParamsTable[1]);

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc)
                .withSchemaName(getSCHEMA())
                .withProcedureName(readProcName);
        Map<String, Object> inParams = new HashMap<>();
        inParams.put(pkColumnName, null);
        inParams.put(P_PAGE_SIZE, size);
        inParams.put(P_PAGE, page);

        Map<String, Object> result = jdbcCall.execute(inParams);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.get("#result-set-1");
        return resultMapper(resultSet);
    }

    @Override
    public T getById(K id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc)
                .withSchemaName(getSCHEMA())
                .withProcedureName(readProcName);
        Map<String, Object> inParams = new HashMap<>();
        inParams.put(pkColumnName, id);
        inParams.put(P_PAGE_SIZE, null);
        inParams.put(P_PAGE, null);

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
