package pl.samba.lms.raport.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.samba.lms.raport.StudentData;
import pl.samba.lms.raport.SubjectInfo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RaportRepository {

    private final JdbcTemplate jdbcTemplate;

    public RaportRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public SubjectInfo getSubjectInfo(int idPrzedmiotu) {
        String sql = "SELECT nazwa, kod FROM lms.przedmioty WHERE id_przedm = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{idPrzedmiotu}, (rs, rowNum) ->
                new SubjectInfo(
                        rs.getString("nazwa"),
                        rs.getString("kod")
                )
        );
    }

    public List<StudentData> getStudentGradesForSubject(int idPrzedmiotu) {
        String sql = "CALL lms.GetStudentGrades(?)";
        return jdbcTemplate.query(sql, new Object[]{idPrzedmiotu}, (rs, rowNum) -> {
            String imie = rs.getString("imie");
            String nazwisko = rs.getString("nazwisko");
            List<Integer> ocenyCzastkowe = Arrays.stream(rs.getString("ocenyCzastkowe").split(","))
                    .filter(str -> !str.isEmpty())
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            Integer ocenaKoncowa = rs.getInt("ocenaKoncowa");
            if (rs.wasNull()) {
                ocenaKoncowa = null;
            }

            return new StudentData(imie, nazwisko, ocenyCzastkowe, ocenaKoncowa);
        });
    }
}