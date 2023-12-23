package pl.samba.lms.przedmioty.zadania.zadania.rodzaje;


import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.samba.lms.przedmioty.zadania.RodzajeZadan;

@AllArgsConstructor
@Getter
public class ZadaniePrawdaFalsz implements ZadanieInterface {
    private String pytanie;
    private String odpowiedz;

    @Override
    public String toString(){
        return  "{" +
                "\"typ\":\""+ RodzajeZadan.PRAWDA_FALSZ +"\"," +
                "\"pytanie\":\""+ pytanie+"\"," +
                "\"odpowiedz\":\""+ odpowiedz+"\"" +
                "}";
    }

}
