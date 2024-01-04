package pl.samba.lms.forum.wpisy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class ForumWpis {
    private final Integer idWpis;
    private final Integer idPrzedm;
    private final Integer idUzytk;
    private final String temat;
    private final String tresc;
    private final LocalDateTime dataWpis;


    @JsonCreator
    public ForumWpis(
            @JsonProperty("idPrzedmiotu") Integer idPrzedmiotu,
            @JsonProperty("idUzytkownika") Integer idUzytkownika,
            @JsonProperty("temat") String temat,
            @JsonProperty("tresc") String tresc,
            @JsonProperty("dataWpis") LocalDateTime dataWpisu
    ){
         this.idWpis = null;
         this.idPrzedm = idPrzedmiotu;
         this.idUzytk = idUzytkownika;
         this.temat = temat;
         this.tresc = tresc;
         this.dataWpis = dataWpisu;
    }
}
