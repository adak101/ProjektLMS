package pl.samba.lms.wiadomosci.prywatne;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class WiadomosciPrywatne {
    private final Integer idWiadomosci;
    private final Integer idNadawcy;
    private final Integer idOdbiorcy;
    private String tresc;
    private Date dataWyslania;
    private Integer idFlagi;

    @JsonCreator
    public WiadomosciPrywatne(
            Integer idWiadomosci,
            Integer idNadawcy,
            Integer idOdbiorcy,
            @JsonProperty("tresc") String tresc,
            @JsonProperty("dataWyslania") Date dataWyslania,
            @JsonProperty("idFlagi") Integer idFlagi
    ){
        this.idWiadomosci = idWiadomosci;
        this.idNadawcy = idNadawcy;
        this.idOdbiorcy = idOdbiorcy;
        this.tresc = tresc;
        this.dataWyslania = dataWyslania;
        this.idFlagi = idFlagi;
    }
}
