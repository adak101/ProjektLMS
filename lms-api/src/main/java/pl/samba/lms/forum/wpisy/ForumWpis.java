package pl.samba.lms.forum.wpisy;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
public class ForumWpis {
    private final Long idWpis;
    private final Long idPrzedm;
    private final Long idUzytk;
    private final String temat;
    private final String tresc;
    private final Date dataWpis;
}
