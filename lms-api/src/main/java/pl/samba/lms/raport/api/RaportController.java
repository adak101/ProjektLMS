package pl.samba.lms.raport.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/raporty")
public class RaportController {

    private final RaportService raportService;

    public RaportController(RaportService raportService) {
        this.raportService = raportService;
    }

    @GetMapping("/generuj")
    public ResponseEntity<byte[]> generujRaport(@RequestParam int idPrzedmiotu) {
        try {
            ByteArrayOutputStream pdfStream = raportService.generateRaport(idPrzedmiotu);
            byte[] pdfBytes = pdfStream.toByteArray();

            return ResponseEntity
                    .ok()
                    .header("Content-Type", "application/pdf")
                    .header("Content-Disposition", "attachment; filename=\"raport_" + idPrzedmiotu + ".pdf\"")
                    .body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
