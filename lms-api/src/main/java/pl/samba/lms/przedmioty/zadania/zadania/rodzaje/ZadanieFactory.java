package pl.samba.lms.przedmioty.zadania.zadania.rodzaje;

import com.google.gson.Gson;
import pl.samba.lms.przedmioty.zadania.RodzajeZadan;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZadanieFactory {
    private static final String TYP = "typ";
    private static final String PYTANIE = "pytanie";
    private static final String ODPOWIEDZ = "odpowiedz";
    private static final String POPRAWNE_ODPOWIEDZI = "poprawneOdp";
    private static final String PLIK = "plik";

    public static List<ZadanieInterface> createZadaniaList(String json){
        List<ZadanieInterface> zadanieList = new ArrayList<>();

        Gson gson = new Gson();
        ArrayList<Map<String, Object>> jsonMapList
                = gson.fromJson(json, ArrayList.class);
        for (Map<String, Object> jsonObject:
                jsonMapList) {
            RodzajeZadan typ = RodzajeZadan.valueOf((String) jsonObject.get(TYP));
            ZadanieInterface zadanie = null;
            switch (typ){
                case OTWARTE -> {
                    zadanie = new ZadanieOtwarte(
                            (String) jsonObject.get(PYTANIE)
                    );

                }
                case ZAMKNIETE -> {
                    List<String> odpowiedzi = (List<String>) jsonObject.get(ODPOWIEDZ);
                    List<Double> poprawneOdp = (List<Double>) jsonObject.get(POPRAWNE_ODPOWIEDZI);

                    zadanie = new ZadanieZamkniete(
                            (String) jsonObject.get(PYTANIE),
                            odpowiedzi,
                            poprawneOdp);
                }
                case PRAWDA_FALSZ -> {
                    zadanie = new ZadaniePrawdaFalsz(
                            (String) jsonObject.get(PYTANIE),
                            (String) jsonObject.get(ODPOWIEDZ)
                    );
                }
                case PLIK -> {
                    zadanie = new ZadaniePlik(
                            (String) jsonObject.get(PYTANIE),
                            ((String) jsonObject.get(PLIK)).getBytes(StandardCharsets.UTF_8)
                    );
                }

            }
            zadanieList.add(zadanie);
        }
        return zadanieList;
    }
}
