package pl.samba.lms.przedmioty.zadania.odpowiedzi.database;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import pl.samba.lms.przedmioty.zadania.ZadanieFactory;
import pl.samba.lms.przedmioty.zadania.odpowiedzi.Odpowiedz;
import pl.samba.lms.przedmioty.zadania.odpowiedzi.rodzaje.OdpowiedzInterface;
import pl.samba.lms.utils.database.AbstractCrudRepository;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class OdpowiedzRepository extends AbstractCrudRepository<Odpowiedz, Integer> {
    private static final String C_ID_ODPOWIEDZI = "id_odpowiedzi";
    private static final String C_ID_ZADANIA = "id_zadania";
    private static final String C_ID_UCZNIA = "id_ucznia";
    private static final String C_TRESC = "tresc";
    private static final String C_KOMENTARZ = "koment";
    private static final String C_OCENA = "ocena";
    private static final String C_DATA_WSTAW = "data_wstaw";
    private static final String C_DATA_OCENY = "data_oceny";

    private static final String P_ID_ZADANIA = "p_id_zadania";
    private static final String P_ID_UCZNIA = "p_id_ucznia";
    private static final String P_TRESC = "p_tresc";
    private static final String P_KOMENTARZ = "p_koment";
    private static final String P_OCENA = "p_ocena";
    private static final String P_DATA_OCENY = "p_data_oceny";

    public OdpowiedzRepository() {
        super("odpowiedzi_zadania", "pk_id_odpowiedzi");
    }

    //todo getByUnique(kod + idUzytkownika)

    @Override
    public Integer save(Odpowiedz data) {
        String tresc = data.getTresc().toString();

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(super.getJdbc())
                .withSchemaName(getSCHEMA())
                .withProcedureName(getInsertProcName());

        Map<String, Object> inParams = new HashMap<>();
        inParams.put(P_ID_ZADANIA, data.getIdZadania());
        inParams.put(P_ID_UCZNIA, data.getIdUcznia());
        inParams.put(P_TRESC,
                Base64.getEncoder().encode(tresc.getBytes(StandardCharsets.UTF_8))
        );
        inParams.put(P_KOMENTARZ, data.getKomentarz());
        inParams.put(P_OCENA, data.getOcena());
        inParams.put(P_DATA_OCENY, data.getDataOcenienia());

        Map<String, Object> result = jdbcCall.execute(inParams);

        return (Integer) result.get(super.getPkColumnName());
    }

    @Override
    public Integer update(Odpowiedz data) {
        String tresc = data.getTresc().toString();

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(super.getJdbc())
                .withSchemaName(getSCHEMA())
                .withProcedureName(getUpdateProcName());

        Map<String, Object> inParams = new HashMap<>();
        inParams.put(getPkColumnName(), data.getIdOdpowiedzi());
        inParams.put(P_ID_ZADANIA, data.getIdZadania());
        inParams.put(P_ID_UCZNIA, data.getIdUcznia());
        inParams.put(P_TRESC,
                Base64.getEncoder().encode(tresc.getBytes(StandardCharsets.UTF_8))
        );
        inParams.put(P_KOMENTARZ, data.getKomentarz());
        inParams.put(P_OCENA, data.getOcena());
        inParams.put(P_DATA_OCENY, data.getDataOcenienia());

        Map<String, Object> result = jdbcCall.execute(inParams);

        return (Integer) result.get(super.getPkColumnName());
    }

    @Override
    public Iterable<Odpowiedz> resultMapper(Iterable<Map<String, Object>> resultSet) {
        List<Odpowiedz> odpowiedzi = new ArrayList<>();

        for(Map<String, Object> row: resultSet){
            byte[] trescBytes = Base64.getDecoder().decode((byte[]) row.get(C_TRESC));

            List<OdpowiedzInterface> odpowiedziList = ZadanieFactory.createOdpowiedziList(
                    new String(trescBytes, StandardCharsets.UTF_8)
            );

            odpowiedzi.add(new Odpowiedz(
                    (Integer) row.get(C_ID_ODPOWIEDZI),
                    (Integer) row.get(C_ID_ZADANIA),
                    (Integer) row.get(C_ID_UCZNIA),
                    odpowiedziList,
                    (String) row.get(C_KOMENTARZ),
                    (Integer) row.get(C_OCENA),
                    (LocalDateTime) row.get(C_DATA_WSTAW),
                    (LocalDateTime) row.get(C_DATA_OCENY)
            ));
        }
        return odpowiedzi;
    }
}
