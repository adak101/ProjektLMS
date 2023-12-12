package pl.samba.lms.przedmioty.przedmioty.database;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import pl.samba.lms.przedmioty.przedmioty.Przedmiot;
import pl.samba.lms.utils.constants.Status;
import pl.samba.lms.utils.database.AbstractCrudRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PrzedmiotRepository extends AbstractCrudRepository<Przedmiot, Integer> {

    public static final String C_ID_PRZEDM = "id_przedm";
    public static final String C_KOD = "kod";
    public static final String C_NAZWA = "nazwa";
    public static final String C_ID_PROW = "id_prow";
    public static final String C_LIMIT_MIEJSC = "limit_miejsc";
    public static final String C_OPIS = "opis";
    public static final String C_WAR_ZALICZ = "war_zalicz";
    public static final String C_ID_OKRESU = "id_okresu";
    public static final String C_KOD_STATUS = "kod_status";
    public static final String C_REJESTR_UCZN = "rejestr_uczn";

    public static final String P_NAZWA = "p_nazwa";
    public static final String P_ID_PROW = "p_id_prow";
    public static final String P_LIMIT_MIEJSC = "p_limit_miejsc";
    public static final String P_OPIS = "p_opis";
    public static final String P_WAR_ZALICZ = "p_war_zalicz";
    public static final String P_ID_OKRESU = "p_id_okresu";
    public static final String P_KOD_STATUS = "p_kod_status";
    public static final String P_REJESTR_UCZN = "p_rejestr_uczn";
    public PrzedmiotRepository() {
        super("przedmioty", "pk_id_przedm");
    }

    @Override
    public Integer save(Przedmiot data) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(super.getJdbc())
                .withSchemaName(getSCHEMA())
                .withProcedureName(super.getInsertProcName());

        Map<String, Object> inParams = new HashMap<>();
        inParams.put(P_NAZWA, data.getNazwa());
        inParams.put(P_ID_PROW, data.getIdProwadzacego());
        inParams.put(P_LIMIT_MIEJSC, data.getLimit());
        inParams.put(P_OPIS, data.getOpis());
        inParams.put(P_WAR_ZALICZ, data.getWarunkiZaliczenia());
        inParams.put(P_ID_OKRESU, data.getIdOkresu());
        inParams.put(P_KOD_STATUS, data.getStatus().getKod());
        inParams.put(P_REJESTR_UCZN, data.getCzyRejestrUczn());

        Map<String, Object> result = jdbcCall.execute(inParams);

        return (Integer) result.get(super.getPkColumnName());
    }

    @Override
    public Integer update(Przedmiot data) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(super.getJdbc())
                .withSchemaName(getSCHEMA())
                .withProcedureName(super.getUpdateProcName());

        Map<String, Object> inParams = new HashMap<>();
        inParams.put(super.getPkColumnName(), data.getIdPrzedmiotu());
        inParams.put(P_NAZWA, data.getNazwa());
        inParams.put(P_ID_PROW, data.getIdProwadzacego());
        inParams.put(P_LIMIT_MIEJSC, data.getLimit());
        inParams.put(P_OPIS, data.getOpis());
        inParams.put(P_WAR_ZALICZ, data.getWarunkiZaliczenia());
        inParams.put(P_ID_OKRESU, data.getIdOkresu());
        inParams.put(P_KOD_STATUS, data.getStatus().getKod());
        inParams.put(P_REJESTR_UCZN, data.getCzyRejestrUczn());

        Map<String, Object> result = jdbcCall.execute(inParams);

        return (Integer) result.get(super.getPkColumnName());
    }

    @Override
    public Iterable<Przedmiot> resultMapper(Iterable<Map<String, Object>> resultSet) {
        List<Przedmiot> przedmiotList = new ArrayList<>();

        for (Map<String, Object> row:
            resultSet ) {
            Przedmiot przedmiot = new Przedmiot(
                    (Integer) row.get(C_ID_PRZEDM),
                    (String) row.get(C_KOD),
                    (String) row.get(C_NAZWA),
                    (Integer) row.get(C_ID_PROW),
                    (Integer) row.get(C_LIMIT_MIEJSC),
                    (String) row.get(C_OPIS),
                    (String) row.get(C_WAR_ZALICZ),
                    (Integer) row.get(C_ID_OKRESU),
                    Status.getStatusByKod(C_KOD_STATUS),
                    (Boolean) row.get(C_REJESTR_UCZN)
            );
            przedmiotList.add(przedmiot);
        }

        return przedmiotList;
    }
}
