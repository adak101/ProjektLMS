package pl.samba.lms.zdjecie;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Zdjecie {
    private final Integer idZdjecia;
    private final byte[] plik;
    private final String nazwa;
    private final String ext;
    private final String alt;
}
