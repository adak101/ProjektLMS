package pl.samba.lms.przedmioty.zadania.zadania.database;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import pl.samba.lms.przedmioty.przedmioty.database.PrzedmiotRepository;
import pl.samba.lms.przedmioty.zadania.ZadanieUtils;
import pl.samba.lms.przedmioty.zadania.zadania.rodzaje.ZadanieInterface;
import pl.samba.lms.przedmioty.zadania.zadania.Zadanie;
import pl.samba.lms.utils.constants.TypyZadan;
import pl.samba.lms.utils.database.AbstractCrudRepository;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class ZadanieRepository extends AbstractCrudRepository<Zadanie, Integer> {

   public static final String C_ID_ZADANIA = "id_zadania";
   public static final String C_ID_PRZEDM = "id_przedm";
   public static final String C_DATA_WSTAW = "data_wstaw";
   public static final String C_DATA_POCZ = "data_pocz";
   public static final String C_DATA_KONC = "data_konc";
   public static final String C_TRESC = "tresc";
   public static final String C_ID_TYPU = "id_typu";

   public static final String P_ID_PRZEDM = "p_id_przedm";
   public static final String P_DATA_POCZ = "p_data_pocz";
   public static final String P_DATA_KONC = "p_data_konc";
   public static final String P_TRESC = "p_tresc";
   public static final String P_ID_TYPU = "p_id_typu";

    public ZadanieRepository() {
        super("zadania", "pk_id_zadania");
    }

    @Override
    public Iterable<Zadanie> getAll(String requestParams) {
        /*
         * requestParamsTable
         * [0] size
         * [1] page
         * [2] kod
         * */

        String[] requestParamsTable = requestParams.split(";");
        Integer size = requestParamsTable[0].isEmpty() ? null : Integer.parseInt(requestParamsTable[0]);
        Integer page = requestParamsTable[1].isEmpty() ? null : Integer.parseInt(requestParamsTable[1]);
        String kod = requestParamsTable[2];

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(super.getJdbc())
                .withSchemaName(getSCHEMA())
                .withProcedureName(super.getReadProcName());
        Map<String, Object> inParams = new HashMap<>();
        inParams.put(super.getPkColumnName(), null);
        inParams.put(getP_PAGE_SIZE(), size);
        inParams.put(getP_PAGE(), page);
        inParams.put(PrzedmiotRepository.P_KOD, kod);

        Map<String, Object> result = jdbcCall.execute(inParams);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.get("#result-set-1");
        if(resultSet.isEmpty()) throw new NoSuchElementException("Brak danych w tabeli '" + super.getTableName() + "' dla size = " + size + ", page = " + page + ", kod = '"+kod+"'!");
        else return resultMapper(resultSet);
    }

    @Override
    public Zadanie getById(Integer id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(super.getJdbc())
                .withSchemaName(getSCHEMA())
                .withProcedureName(super.getReadProcName());
        Map<String, Object> inParams = new HashMap<>();
        inParams.put(super.getPkColumnName(), id);
        inParams.put(getP_PAGE_SIZE(), null);
        inParams.put(getP_PAGE(), null);
        inParams.put(PrzedmiotRepository.P_KOD, null);

        Map<String, Object> result = jdbcCall.execute(inParams);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.get("#result-set-1");

        if(resultSet.isEmpty()) throw new NoSuchElementException("Brak danych dla klucza głównego id = " + id + "!");
        else return resultMapper(resultSet).iterator().next();
    }

    @Override
    public Integer save(Zadanie data) {
        String tresc = data.getTresc().toString();

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(super.getJdbc())
                .withSchemaName(getSCHEMA())
                .withProcedureName(getInsertProcName());

        Map<String, Object> inParams = new HashMap<>();
        inParams.put(P_ID_PRZEDM, data.getIdPrzedmiotu());
        inParams.put(P_DATA_POCZ, data.getDataPoczatku());
        inParams.put(P_DATA_KONC, data.getDataKonca());
        inParams.put(P_TRESC,
                Base64.getEncoder().encode(tresc.getBytes(StandardCharsets.UTF_8))
        );
        inParams.put(P_ID_TYPU, data.getTypyZadania().getId());

        Map<String, Object> result = jdbcCall.execute(inParams);

        return (Integer) result.get(super.getPkColumnName());
    }

    @Override
    public Integer update(Zadanie data) {
        String tresc = data.getTresc().toString();

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(super.getJdbc())
                .withSchemaName(getSCHEMA())
                .withProcedureName(getUpdateProcName());

        Map<String, Object> inParams = new HashMap<>();
        inParams.put(getPkColumnName(), data.getIdZadania());
        inParams.put(P_ID_PRZEDM, data.getIdPrzedmiotu());
        inParams.put(P_DATA_POCZ, data.getDataPoczatku());
        inParams.put(P_DATA_KONC, data.getDataKonca());
        inParams.put(P_TRESC,
                Base64.getEncoder().encode(tresc.getBytes(StandardCharsets.UTF_8))
        );
        inParams.put(P_ID_TYPU, data.getTypyZadania().getId());

        Map<String, Object> result = jdbcCall.execute(inParams);

        return (Integer) result.get(super.getPkColumnName());
    }

    @Override
    public Iterable<Zadanie> resultMapper(Iterable<Map<String, Object>> resultSet) {
        List<Zadanie> zadania = new ArrayList<>();

        for (Map<String, Object> row:
             resultSet) {
            byte[] trescBytes =
            Base64.getDecoder().decode((byte[]) row.get(C_TRESC));
            List<ZadanieInterface> zadaniaList = ZadanieUtils.zadaniaFactory(
                    new String(
                            trescBytes,
                            StandardCharsets.UTF_8)
            );

            zadania.add(new Zadanie(
                    (Integer) row.get(C_ID_ZADANIA),
                    (Integer) row.get(C_ID_PRZEDM),
                    TypyZadan.getById((Integer) row.get(C_ID_TYPU)),
                    (LocalDateTime) row.get(C_DATA_WSTAW),
                    (LocalDateTime) row.get(C_DATA_POCZ),
                    (LocalDateTime) row.get(C_DATA_KONC),
                    zadaniaList
            ));
        }
        return zadania;
    }
}
