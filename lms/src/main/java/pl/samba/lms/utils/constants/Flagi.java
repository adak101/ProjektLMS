package pl.samba.lms.utils.constants;

/**
 * Reprezentuje różne flagi stanu dla  wiadomości i powiadomień.
 * Może być używana do określenia statusu, przejścia przez etapy cyklu życia, czy innych wariantów.
 */
public enum Flagi {
    NIEPRZECZYTANA,   // Oznacza, że obiekt nie został jeszcze przeczytany.
    PRZECZYTANA,      // Oznacza, że obiekt został przeczytany.
    ZARCHIWIZOWANA,   // Oznacza, że obiekt został zarchiwizowany.
    USUNIETA,         // Oznacza, że obiekt został usunięty.
    NOWA,             // Oznacza, że obiekt jest nowy.
    ROBOCZA           // Oznacza, że obiekt jest wersją roboczą lub tymczasową.
}

