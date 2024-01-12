/*package pl.samba.lms.wiadomosci.prywatne.database;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import pl.samba.lms.utils.database.AbstractCrudRepository;
import pl.samba.lms.wiadomosci.prywatne.WiadomosciPrywatne;

import java.util.*;

@Repository
public class WiadomosciPrywatneRepository extends AbstractCrudRepository<WiadomosciPrywatne, Integer> {

    public static final String C_ID_WIADOMOSCI = "id_wiadomosci";
    public static final String C_ID_NADAWCY = "id_nadawcy";
    public static final String C_ID_ODBIORCY = "id_odbiorcy";
    public static final String C_TRESC = "tresc";
    public static final String C_DATA_WYSLANIA = "data_wyslania";
    public static final String C_ID_FLAGI = "id_flagi";
    private SimpleJdbcCall jdbcCall;

    @Override
    public Iterable<WiadomosciPrywatne> getAll(String requestParams) {
        String[] requestParamsTable = requestParams.split(";");


        Integer size = requestParamsTable[0].isEmpty() ? null : Integer.parseInt(requestParamsTable[0]);
        Integer page = requestParamsTable[1].isEmpty() ? null : Integer.parseInt(requestParamsTable[1]);
        // Dodatkowe parametry, jeśli są potrzebne, można dodać tutaj

        Map<String, Object> inParams = new HashMap<>();
        inParams.put(super.getPkColumnName(), null); // Klucz główny ustawiony na null, ponieważ pobieramy wszystkie rekordy
        inParams.put(P_SIZE, size); // Przekazanie rozmiaru strony
        inParams.put(P_PAGE, page); // Przekazanie numeru strony

        Map<String, Object> result = jdbcCall.execute(inParams);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.get("#result-set-1");
        if (resultSet.isEmpty()) {
            throw new NoSuchElementException("Brak danych w tabeli '" + super.getTableName() + "' dla size=" + size + ", page=" + page + "!");
        } else {
            return resultMapper(resultSet);
        }
    }
*/