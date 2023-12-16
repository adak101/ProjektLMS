# LMS-API DOC

Dokumentacja do aplikacji rest api projektu LSM.

Stan na dzień 16.12.2023 r.

## Spis treści

1. [Zadeklarowane stałe](#zadeklarowane-stałe)
    - [Statusy](#statusy)
    - [Role](#role)
    - [Flagi](#flagi)
2. [Użytkownicy](#użytkownicy)
    - [Pobieranie listy wszystkich użytkowników](#1-pobieranie-listy-wszystkich-użytkowników)
    - [Pobieranie pojedynczego użytkownika](#2-pobieranie-pojedynczego-użytkownika)
    - [Pobieranie pojedynczego użytkownika po loginie](#3-pobieranie-pojedynczego-użytkownika-po-loginie)
    - [Usuwanie użytkownika](#4-usuwanie-użytkownika)
    - [Dodawanie nowego użytkownika](#5-dodawanie-nowego-użytkownika)
    - [Aktualizacja danych użytkownika](#6-aktualizacja-danych-użytkownika)
3. [Przedmioty](#przedmioty)
    - [Pobieranie listy wszystkich przedmiotów](#1-pobieranie-listy-wszystkich-przedmiotów)
    - [Pobieranie pojedynczego przedmiotu](#2-pobieranie-pojedynczego-przedmiotu)
    - [Pobieranie pojedynczego przedmiotu po kodzie](#3-pobieranie-pojedynczego-przedmiotu-po-kodzie)
    - [Usuwanie przedmiotu](#4-usuwanie-przedmiotu)
    - [Dodawanie nowego przedmiotu](#5-dodawanie-nowego-przedmiotu)
    - [Aktualizacja danych przedmiotu](#6-aktualizacja-danych-przedmiotu)
4. [Okresy](#okresy)
    - [Pobieranie listy wszystkich okresów](#1-pobieranie-listy-wszystkich-okresów)
    - [Pobieranie pojedynczego okresu](#2-pobieranie-pojedynczego-okresu)
    - [Usuwanie okresu](#3-usuwanie-okresu)
    - [Dodawanie nowego okresu](#4-dodawanie-nowego-okresu)
    - [Aktualizacja danych okresu](#5-aktualizacja-danych-okresu)
5. [Rejestracja](#rejestracja)
    - [Rejestracja ucznia na przedmiot](#1-rejestracja-ucznia-na-przedmiot)
    - [Pobieranie listy lub konkretnego powiązania](#2-pobieranie-listy-lub-konkretnego-powiązania)
    - [Pobieranie powiązania o konkretnym numerze ID](#3-pobieranie-powiązania-o-konkretnym-numerze-id)
    - [Wystawienie oceny uczniowi](#4-wystawienie-oceny-uczniowi)
    - [Wyrejestrowanie ucznia z przedmiotu](#5-wyrejestrowanie-ucznia-z-przedmiotu)
---
## Zadeklarowane stałe

### Statusy
1. `DO_ZATWIERDZENIA (2AP)` - Oczekuje na zatwierdzenie.
2. `ZATWIERDZONY (APR)` - Został zatwierdzony.
3. `ODRZUCONY (REJ)` - Został odrzucony.
4. `TRWAJACY (ACT)`- Jest aktualnie w trakcie.
5. `ZAKONCZONY (END)`- Zakończony.
6. `AKTYWNY (1)`- Aktywny.
7. `NIE_AKTYWNY (0)`- Nieaktywny.

### Role
1. `ADMIN (1)`
2. `NAUCZYCIEL (2)` 
3. `UCZEN (3)`

### Flagi
1. `NIEPRZECZYTANA` - Oznacza, że obiekt nie został jeszcze przeczytany.
2. `PRZECZYTANA` - Oznacza, że obiekt został przeczytany. 
3. `ZARCHIWIZOWANA`- Oznacza, że obiekt został zarchiwizowany. 
4. `USUNIETA` - Oznacza, że obiekt został usunięty. 
5. `NOWA` - Oznacza, że obiekt jest nowy. 
6. `ROBOCZA`  - Oznacza, że obiekt jest wersją roboczą lub tymczasową.

## Użytkownicy

### Opis

Klasa `UzytkownicyController` odpowiada za obsługę end-pointów związanych z zarządzaniem użytkownikami w systemie. W ramach dokumentacji przedstawione są wszystkie dostępne end-pointy wraz z ich opisem, parametrami i możliwymi odpowiedziami.

### End-pointy
#### 1. Pobieranie listy wszystkich użytkowników
```
GET /api/uzytkownik/all
```
   - Ścieżka: `/api/uzytkownik/all`
   - Metoda: `GET`
   - Parametry:
     - `size` (opcjonalny): liczba elementów na stronie
     - `page` (opcjonalny): numer strony (liczony od 0)
   - Odpowiedź:
     - `200 OK` - sukces, zwraca listę użytkowników w formacie JSON
     - `404 Not Found` - brak użytkowników

```text
GET /api/uzytkownik/all
```     
#### 2. Pobieranie pojedynczego użytkownika

   - Ścieżka: `/api/uzytkownik/{id}`
   - Metoda: `GET`
   - Parametry:
     - `id` (ścieżka): identyfikator użytkownika
   - Odpowiedź:
     - `200 OK` - sukces, zwraca dane użytkownika w formacie JSON
     - `404 Not Found` - użytkownik o podanym identyfikatorze nie istnieje

```text
GET /api/uzytkownik/1
```
#### 3. Pobieranie pojedynczego użytkownika po loginie

   - Ścieżka: `/api/uzytkownik`
   - Metoda: `GET`
   - Parametry:
     - `login` (parametr zapytania): login użytkownika (zakodowany w Base64)
   - Odpowiedź:
     - `200 OK` - sukces, zwraca dane użytkownika w formacie JSON
     - `404 Not Found` - użytkownik o podanym loginie nie istnieje
   

```text
GET /api/uzytkownik/?login=<login_base_64>
```
#### 4. Usuwanie użytkownika

   - Ścieżka: `/api/uzytkownik/{id}`
   - Metoda: `DELETE`
   - Parametry:
     - `id` (ścieżka): identyfikator użytkownika
   - Odpowiedź: 
     - `204 No Content` - sukces, użytkownik został usunięty 
     - `404 Not Found` - użytkownik o podanym identyfikatorze nie istnieje

```text
DELETE /api/uzytkownik/<nr_id>
```
#### 5. Dodawanie nowego użytkownika

   - Ścieżka: `/api/uzytkownik`
   - Metoda: `POST`
   - Parametry:
     - Ciało żądania zawiera dane nowego użytkownika w formacie JSON
   - Odpowiedź:
     - `201 Created` - sukces, użytkownik został dodany, zwraca link do nowo utworzonego użytkownika
     - `400 Bad Request` - błąd w danych wejściowych
   
> WAŻNE!
> 
> Login użytkownika nadawany jest automatycznie w konwencji <pierwsza_litera_imienia>.<nazwisko>[?nr_porządkowy]
> 
```text
POST /api/uzytkownik
Content-Type: application/json
```
```json
{
    "imie": "Jan",
    "nazwisko": "Kowalski",
    "tytNauk": null,
    "email": "john.doe@example.com",
    "telefon": 123456789,
    "dataUrodz": "1990-01-01",
    "status": "AKTYWNY",
    "rola": "UCZEN",
    "zdjecie": {
        "plik": "<plik_binarny_base_64>",
        "nazwa": "zdjecie",
        "ext": "png",
        "alt": "zdjecie-uzytkownika"
    }
}
```
#### 6. Aktualizacja danych użytkownika

   - Ścieżka: `/api/uzytkownik/{id}`
   - Metoda: `PATCH`
   - Parametry:
     - `id` (ścieżka): identyfikator użytkownika
     - Ciało żądania zawiera dane do aktualizacji w formacie JSON
   -Odpowiedź:
     - `200 OK` - sukces, użytkownik został zaktualizowany, zwraca link do zaktualizowanego użytkownika
     - `400 Bad Request` - błąd w danych wejściowych
     - `404 Not Found` - użytkownik o podanym identyfikatorze nie istnieje

Przykład:
```text
PATCH /api/uzytkownik/1
Content-Type: application/json
```
```json
{
  "nazwisko": "Smith",
  "email": "john.smith@example.com"
}

```

## Przedmioty

### Opis

Klasa `PrzedmiotyController` odpowiada za obsługę end-pointów związanych z zarządzaniem przedmiotami w systemie. W ramach dokumentacji przedstawione są wszystkie dostępne end-pointy wraz z ich opisem, parametrami i możliwymi odpowiedziami.

### End-pointy

#### 1. Pobieranie listy wszystkich przedmiotów
- Ścieżka: `/api/przedmiot/all`
- Metoda: `GET`
- Parametry:
  - `size` (opcjonalny): liczba elementów na stronie
  - `page` (opcjonalny): numer strony (liczony od 0) (liczony od 0)
- Odpowiedź:
  - `200 OK` - sukces, zwraca listę przedmiotów w formacie JSON
  - `404 Not Found` - brak przedmiotów

```text
GET /api/przedmiot/all
```

#### 2. Pobieranie pojedynczego przedmiotu

- Ścieżka: `/api/przedmiot/{id}`
- Metoda: `GET`
- Parametry:
    - `id` (ścieżka): identyfikator przedmiotu
- Odpowiedź:
    - `200 OK` - sukces, zwraca dane przedmiotu w formacie JSON
    - `404 Not Found` - przedmiot o podanym identyfikatorze nie istnieje

```text
GET /api/przedmiot/1
```

#### 3. Pobieranie pojedynczego przedmiotu po kodzie

- Ścieżka: `/api/przedmiot`
- Metoda: `GET`
- Parametry:
    - `kod` (parametr zapytania): kod przedmiotu (zakodowany w Base64)
- Odpowiedź:
  - `200 OK` - sukces, zwraca dane przedmiotu w formacie JSON
  - `404 Not Found` - przedmiot o podanym kodzie nie istnieje

```text
GET /api/przedmiot/?kod=<kod_base_64>
```

#### 4. Usuwanie przedmiotu

- Ścieżka: `/api/przedmiot/{id}`
- Metoda: `DELETE`
- Parametry:
   - `id` (ścieżka): identyfikator przedmiotu
- Odpowiedź:
   - `204 No Content`- sukces, przedmiot został usunięty
  - `404 Not Found` - przedmiot o podanym identyfikatorze nie istnieje

```text
DELETE /api/przedmiot/<nr_id>
```

#### 5. Dodawanie nowego przedmiotu

- Ścieżka: `/api/przedmiot`
- Metoda: `POST`
- Parametry:
    - Ciało żądania zawiera dane nowego przedmiotu w formacie JSON
- Odpowiedź:
  -  `201 Created` - sukces, przedmiot został dodany, zwraca link do nowo utworzonego przedmiotu
  -  `400 Bad Request` - błąd w danych wejściowych

> WAŻNE!

> Kod przedmiotu nadawany jest automatycznie w konwencji:
> 
> <3 znaki okresu>/<4 znaki nazwy przedmiotu>/<numer porządkowy>


```text
POST /api/przedmiot
Content-Type: application/json
```

```json
{
    "nazwa": "Matematyka",
    "idProwadzacego": 123,
    "limit": 30,
    "opis": "Przedmiot z zakresu matematyki",
    "warunkiZaliczenia": "Egzamin końcowy",
    "idOkresu": 1,
    "status": "AKTYWNY", 
    "czyRejestrUczn": true
}
```

#### 6. Aktualizacja danych przedmiotu

- Ścieżka: `/api/przedmiot/{id}`
- Metoda: `PATCH`
- Parametry:
    - `id` (ścieżka): identyfikator przedmiotu
    - Ciało żądania zawiera dane do aktualizacji w formacie JSON
- Odpowiedź:
    - `200 OK` - sukces, przedmiot został zaktualizowany, zwraca link do zaktualizowanego przedmiotu
    - `400 Bad Request` - błąd w danych wejściowych
    - `404 Not Found` - przedmiot o podanym identyfikatorze nie istnieje

```text

PATCH /api/przedmiot/1
Content-Type: application/json
```
```json
{
    "nazwa": "Fizyka",
    "opis": "Przedmiot z zakresu fizyki"
}
```
## Okresy

### Opis

Klasa `OkresyController `odpowiada za obsługę end-pointów związanych z zarządzaniem okresami przedmiotów w systemie. W ramach dokumentacji przedstawione są wszystkie dostępne end-pointy wraz z ich opisem, parametrami i możliwymi odpowiedziami.

### End-pointy

#### 1. Pobieranie listy wszystkich okresów
- Ścieżka: `/api/przedmiot/okres/all`
- Metoda: `GET`
- Parametry:
  - `size` (opcjonalny): liczba elementów na stronie
  - `page` (opcjonalny): numer strony (liczony od 0)
- Odpowiedź:
  - `200 OK` - sukces, zwraca listę okresów w formacie JSON
  - `404 Not Found` - brak okresów

```text
GET /api/przedmiot/okres/all
```

#### 2. Pobieranie pojedynczego okresu

- Ścieżka: `/api/przedmiot/okres/{id}`
- Metoda: `GET`
- Parametry:
    `id` (ścieżka): identyfikator okresu
- Odpowiedź:
    `200 OK` - sukces, zwraca dane okresu w formacie JSON
    `404 Not Found `- okres o podanym identyfikatorze nie istnieje

```text
GET /api/przedmiot/okres/1
```
#### 3. Usuwanie okresu

- Ścieżka: `/api/przedmiot/okres/{id}`
- Metoda: `DELETE`
- Parametry:
    - `id` (ścieżka): identyfikator okresu
- Odpowiedź:
    - `204 No Content` - sukces, okres został usunięty
    - `404 Not Found` - okres o podanym identyfikatorze nie istnieje

```text
DELETE /api/przedmiot/okres/<nr_id>
```
#### 4. Dodawanie nowego okresu

- Ścieżka: `/api/przedmiot/okres`
- Metoda: `POST`
- Parametry:
    - Ciało żądania zawiera dane nowego okresu w formacie JSON
- Odpowiedź:
    - `201 Created` - sukces, okres został dodany, zwraca link do nowo utworzonego okresu
    - `400 Bad Request` - błąd w danych wejściowych

```text
POST /api/przedmiot/okres
Content-Type: application/json
```
```json
{
    "kod": "SEM01",
    "dataPoczatku": "2023-01-01",
    "dataKonca": "2023-02-28"
}
```
#### 5. Aktualizacja danych okresu

- Ścieżka: `/api/przedmiot/okres/{id}`
- Metoda: `PATCH`
- Parametry:
    - `id` (ścieżka): identyfikator okresu
  - Ciało żądania zawiera dane do aktualizacji w formacie JSON
- Odpowiedź:
  - `200 OK `- sukces, okres został zaktualizowany, zwraca link do zaktualizowanego okresu
  - `400 Bad Request` - błąd w danych wejściowych
  - `404 Not Found` - okres o podanym identyfikatorze nie istnieje

```text
PATCH /api/przedmiot/okres/1
Content-Type: application/json
```
```json
{
    "dataPoczatku": "2023-02-01",
    "dataKonca": "2023-03-31"
}
```

## Rejestracja

### Opis

Klasa UczenPrzedmiotController odpowiada za obsługę end-pointów związanych z rejestracją ucznia na przedmiot w systemie i wystawianiem mu oceny. W ramach dokumentacji przedstawione są wszystkie dostępne end-pointy wraz z ich opisem, parametrami i możliwymi odpowiedziami.

### End-pointy

#### 1. Rejestracja ucznia na przedmiot
- Ścieżka: `/api/przedmiot/uczen/rejestruj`
- Metoda: `POST`
- Parametry:
    - `nick` (parametr zapytania, wymagany): nick (login) ucznia w formacie Base64
  - `kod` (parametr zapytania, wymagany): kod przedmiotu w formacie Base64
- Odpowiedź:
    - `201 Created` - sukces, uczniowi został przypisany przedmiot
  - `400 Bad Request` - błąd w danych wejściowych

```text
POST /api/przedmiot/uczen/rejestruj?nick=<nick_base64>&kod=<kod_base64>
```
#### 2. Pobieranie listy lub konkretnego powiązania:

Powiązanie można pobrać po:
* **nicku (loginie)** ucznia wtedy jest to lista wszystkich przedmiotow ucznia
* **kodzie przedmiotu**, wtedy jest to lista wszystkich uczniow w danym przedmiocie
* **nicku i kodzie**, wtedy pobiera konkretny poziazanie ucznia i przedmiotu
* **bez żadnych parametrów**, wtedy pobiera wszytko

- Ścieżka: `/api/przedmiot/uczen`
- Metoda: GET
- Parametry:
  - `nick` (parametr zapytania, opcjonalny): nick (login) ucznia w formacie Base64
  - `kod` (parametr zapytania, opcjonalny): kod przedmiotu w formacie Base64
  - `size` (parametr zapytania, opcjonalny): liczba wyników na stronie
  - `page` (parametr zapytania, opcjonalny): numer strony, liczony od 0
- Odpowiedź:
  - `200` OK - sukces, zwraca kolekcję modeli powiązań w formacie JSON
  - `404` Not Found - brak powiązań

```text
GET /api/przedmiot/uczen?nick=<nick_base64>&kod=<kod_base64>&size=<size>&page=<page>
```
#### 3. Pobieranie powiązania o konkretnym numerze ID

- Ścieżka: `/api/przedmiot/uczen/{id}`
- Metoda: `GET`
- Parametry:
  - `id` (ścieżka): numer ID powiązania
- Odpowiedź:
  - `200 OK` - sukces, zwraca model powiązania w formacie JSON
  - `404 Not Found` - brak powiązania o podanym ID

```text
GET /api/przedmiot/uczen/{id}
```

#### 4. Wystawienie oceny uczniowi

- Ścieżka: `/api/przedmiot/uczen/ocena`
- Metoda: `PATCH`
- Parametry:
    - `ocena` (parametr zapytania, wymagany): ocena do wystawienia
    - `nick` (parametr zapytania, wymagany): nick (login) ucznia w formacie Base64
    - `kod` (parametr zapytania, wymagany): kod przedmiotu w formacie Base64
- Odpowiedź:
  - `200 OK` - sukces, ocena została wystawiona
  - `404 Not Found` - brak powiązania o podanym nicku i kodzie

```text
PATCH /api/przedmiot/uczen/ocena?ocena=<ocena>&nick=<nick_base64>&kod=<kod_base64>
```

#### 5. Wyrejestrowanie ucznia z przedmiotu

- Ścieżka: `/api/przedmiot/uczen/wyrejestruj`
- Metoda: `DELETE`
- Parametry:
    - `nick` (parametr zapytania, wymagany): nick (login) ucznia w formacie Base64
  - `kod` (parametr zapytania, wymagany): kod przedmiotu w formacie Base64
- Odpowiedź:
    - `204 No Content` - sukces, ucznia został wyrejestrowany z przedmiotu
  - `404 Not Found` - brak powiązania o podanym nicku i kodzie

```text
DELETE /api/przedmiot/uczen/wyrejestruj?nick=<nick_base64>&kod=<kod_base64>
```

