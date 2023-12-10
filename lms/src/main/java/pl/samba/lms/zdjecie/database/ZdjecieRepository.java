package pl.samba.lms.zdjecie.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import pl.samba.lms.utils.database.AbstractCrudRepository;
import pl.samba.lms.uzytkownicy.Uzytkownik;
import pl.samba.lms.zdjecie.Zdjecie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ZdjecieRepository extends AbstractCrudRepository<Zdjecie, Integer> {

    private static final String P_ZDJECIE = "p_zdjecie";
    private static final String P_ALT = "p_alt";
    private static final String P_EXT = "p_ext";
    private static final String P_NAZWA = "p_nazwa";
    @Autowired
    public ZdjecieRepository(JdbcTemplate jdbc) {
        super(jdbc, "zdjecia", "pk_id_zdjecia");
    }

    @Override
    public Integer save(Zdjecie data) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(super.getJdbc())
                .withSchemaName(getSCHEMA())
                .withProcedureName(super.getInsertProcName());
        Map<String, Object> inParams = new HashMap<>();
        inParams.put(P_ZDJECIE, data.getPlik());
        inParams.put(P_ALT, data.getAlt());
        inParams.put(P_EXT, data.getExt());
        inParams.put(P_NAZWA, data.getNazwa());

        Map<String, Object> result = jdbcCall.execute(inParams);

        return (Integer) result.get(super.getPkColumnName());
    }

    @Override
    public Integer update(Zdjecie data) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(super.getJdbc())
                .withSchemaName(getSCHEMA())
                .withProcedureName(super.getUpdateProcName());
        Map<String, Object> inParams = new HashMap<>();
        inParams.put(super.getPkColumnName(), data.getIdZdjecia());
        inParams.put(P_ZDJECIE, data.getPlik());
        inParams.put(P_ALT, data.getAlt());
        inParams.put(P_EXT, data.getExt());
        inParams.put(P_NAZWA, data.getNazwa());

        Map<String, Object> result = jdbcCall.execute(inParams);

        return (Integer) result.get(super.getPkColumnName());
    }

    @Override
    public Iterable<Zdjecie> resultMapper(Iterable<Map<String, Object>> resultSet) {
        List<Zdjecie> zdjecieList = new ArrayList<>();
        for(Map<String, Object> row: resultSet){
            Zdjecie zdjecie = new Zdjecie(
                    (Integer) row.get("id_zdjecia"),
                    (byte[]) row.get("plik"),
                    (String) row.get("nazwa_pliku"),
                    (String) row.get("ext"),
                    (String) row.get("alt"));

            zdjecieList.add(zdjecie);
        }
        return zdjecieList;
    }
}
