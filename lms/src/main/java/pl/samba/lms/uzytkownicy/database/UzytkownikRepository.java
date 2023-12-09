package pl.samba.lms.uzytkownicy.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import pl.samba.lms.utils.constants.Status;
import pl.samba.lms.zdjecie.Zdjecie;
import pl.samba.lms.utils.database.AbstractCrudRepository;
import pl.samba.lms.utils.constants.Role;
import pl.samba.lms.uzytkownicy.Uzytkownik;
import pl.samba.lms.zdjecie.database.ZdjecieRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bsurma
 */
@Repository
public class UzytkownikRepository extends AbstractCrudRepository<Uzytkownik, Integer> {

    private static final String P_IMIE = "p_imie";
    private static final String P_NAZWISKO = "p_nazwisko";
    private static final String P_TYT_NAUK = "p_tyt_nauk";
    private static final String P_HASLO = "p_haslo";
    private static final String P_EMAIL = "p_email";
    private static final String P_TELEFON = "p_telefon";
    private static final String P_DATA_URODZ = "p_data_urodz";
    private static final String P_ID_ZDJECIA = "p_id_zdjecia";
    private static final String P_ID_ROLI = "p_id_roli";

    private final ZdjecieRepository dsZdjecia;
    @Autowired
    public UzytkownikRepository(JdbcTemplate jdbc, ZdjecieRepository dsZdjecia) {
        super(jdbc, "uzytkownicy","pk_id_uzytk");
        this.dsZdjecia = dsZdjecia;
    }


    @Override
    public Integer save(Uzytkownik data) {
        Integer idZdjecia = 1;
        if(data.getZdjecie() != null){
            idZdjecia = dsZdjecia.save(data.getZdjecie());
        }


        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(super.getJdbc())
                .withSchemaName(getSCHEMA())
                .withProcedureName(super.getInsertProcName());
        Map<String, Object> inParams = new HashMap<>();
        inParams.put(P_IMIE, data.getImie());
        inParams.put(P_NAZWISKO, data.getNazwisko());
        inParams.put(P_TYT_NAUK, data.getTytNauk());
        inParams.put(P_HASLO, data.getHaslo());
        inParams.put(P_EMAIL, data.getEmail());
        inParams.put(P_TELEFON, data.getTelefon());
        inParams.put(P_DATA_URODZ, data.getDataUrodz());
        inParams.put(P_ID_ZDJECIA, idZdjecia);
        inParams.put(P_ID_ROLI, data.getRola().getId());

        Map<String, Object> result = jdbcCall.execute(inParams);

        return (Integer) result.get(super.getPkColumnName());
    }

    @Override
    public Integer update(Uzytkownik data) {
        return null;
    }

    @Override
    public Iterable<Uzytkownik> resultMapper(Iterable<Map<String, Object>> resultSet) {
        List<Uzytkownik> uzytkownicy = new ArrayList<>();
        for (Map<String, Object> row : resultSet) {
            Zdjecie zdjecie = new Zdjecie(
                    (Integer) row.get("id_zdjecia"),
                    (byte[]) row.get("plik"),
                    (String) row.get("nazwa_pliku"),
                    (String) row.get("ext"),
                    (String) row.get("alt"));

            Uzytkownik uzytkownik = new Uzytkownik(
                    (Integer) row.get("id_uzytk"),
                    (String) row.get("imie"),
                    (String) row.get("nazwisko"),
                    (String) row.get("tyt_nauk"),
                    (String) row.get("login"),
                    (String) row.get("haslo"),
                    (String) row.get("email"),
                    (Integer) row.get("telefon"),
                    (java.sql.Date) row.get("data_urodz"),
                    Status.getStatusByKod(row.get("status").toString()),
                    zdjecie,
                    Role.valueOf((String) row.get("rola"))
            );

            uzytkownicy.add(uzytkownik);
        }
        return uzytkownicy;
    }

}
