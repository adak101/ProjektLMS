# Raport z postępu projektu *Systemu Zarządzania Nauką Online (LMS)*

na dzień: 04-01-2024 r.

## Założenia projektu

Opracowanie kompleksowego systemu zarządzania nauczaniem (LMS) dla szkół lub uniwersytetów do zarządzania kursami, zadaniami, ocenami i profilami użytkowników.

## Członkowie Zespołu

- Ernest Milecki,
- Mikołaj Noga,
- Bartosz Surma,
- Szymon Świercz,
- Adam Waśko

## Wykonane prace

W tym miesiącu postęp prac w warstwie _back-end_'owej obiął utworzenie pierwszych dwóch wersji REST API dla LMS. Została opracowana całościowa struktura projektu (sposób w jaki kod ma być dzielony na pakiety), która ma na celu ułatwienie analizy kodu i dalszego utrzymania oraz rozwijania aplikacji. Opracowane zostały  klasy abstrakcyjne, interfejsy oraz narzędzia pomocnicze, które ułatwiają budowanie API, które obejmuje łączenie się z bazą danych oraz wysyłanie i odbieranie danych poprzez protokół HTTP. 

Baza danych została rozbudowana o nowe, potrzebne kolumny w poszczególnych tabelach. Ponad to zostały utworzone albo poprawione indeksy i klucze unikalne, które zabezpieczają bazę danych przed nieprawidłowym wstawianiem danych oraz usprawniają ich pobieranie, a także _triggery_, część których służy do walidacji wprowadzanych danych, a część do dodawania powiadomień. Do dodawania powiadomień utworzony również został _event_, który cyklicznie sprawdza dostępność zadań i na tej podstawie dodaje odpowiednie powiadomienia.

Wraz z tworzeniem i rozbudową REST API tworzona jest dokumentacja techniczna, która zawiera opis dostępnych _end-point_'ów oraz instrukcję korzystania z nich. 

Dla obecnej wersji aplikacji dostępne jest:
   - Rejestracja oraz logowanie użytkownika (Autentykacja przy użyciu tokenu _JWT_),
   - Dodawanie, pobieranie, aktualizowanie i usuwanie danych użytkowników oraz zdjęć,
   - Dodawanie, pobieranie, aktualizowanie i usuwanie przedmiotów,
   - Dodawanie, pobieranie, aktualizowanie i usuwanie okresów (semestrów) dla przedmiotów,
   - Rejestracja ucznia na przedmiot, pobieranie danych danego powiązania, wystawienie oceny uczniowi oraz wyrejestrowanie ucznia z przedmiotu,
   - Dodawanie, pobieranie, usuwanie i aktualizacja zadań do danego materiału i przedmiotu,
   - Dodawanie, pobieranie, usuwanie, aktualizacja odpowiedzi do zadań oraz możliwość wystawienia oceny i komentarza przez nauczyciela do danego zadania,
   -  Dodawanie, pobieranie, usuwanie, aktualizacja materiałów do danego przedmiotu,
   - Pobieranie i usuwanie powiadomień oraz aktualizacja flagi powiadomienia,
   - Dodawanie, pobieranie, usuwanie, aktualizacja wpisu na forum (brak w dokumentacji),
   - Dodawanie, pobieranie, usuwanie, aktualzacja komentarza do wpisu na forum (brak w dokumentacji).

Dokumentacja dostępna w pliku `API-DOC.pdf`

Aktualny schemat bazy danych:
![](out\schema\lms-database.png)

---

Praca w technologii: `React`, `Tailwind`

Na warstwie _front-end_'owej postęp prac obejmuje wytworzenie szablonu całej aplikacji - utworzenie paneli i uzupełnienie ich statyczymi danymi. 

Cały kod projektu został podzielony na odpowiednie pliki i jest utrzymywany w odpowiedniej strukturze plików w sposób logiczny, ułatwiający dalszy rozwój i utrzymanie aplikacji.

Po wytworzeniu pierwszej wersji API możliwe było połączenie się z nim i pobieranie danych w celu uzupełniania paneli odpowiednimi danymi pobranymi z bazy danych.

Główny panel ucznia
![](images\414534414_2092906924377189_5301800074108654631_n.jpg)

Panel dostępnych przedmiotów:
![](images\414274880_2456870154483912_5501570640197184088_n.jpg)

Panel przeglądu przedmiotu:
![](images\414729143_1166365810995897_7500647940123884354_n.png)

Panel przeglądu przedmiotu (informacje o przedmiocie):
![](images\412161837_670737668542830_2910490169260868813_n.jpg)

Panel przeglądu tematu (materiału) w danym przedmiocie:
![](images\414759425_414019334288022_99597901408398629_n.png)

Panel dostępnych testów:
![](images\414937308_7067758049984233_4551843521138968408_n.png)

Panel ocen:
![](images\413333985_382000040947152_4134064542933507865_n.jpg)

Panel użytkownika:
![](images\414729143_3587828784838821_1971459563016707868_n.png)


---

Całość kodu projektu jest sukcesywnie umieszczana w repozytorium na platforimie _github_. Każda zmiana odbywa się na odpowiednim _branchu_ i jest podzielona na odpowiednie _commity_.

---

Cele na kolejny miesiąc:

1. Rozbudowa API oraz bazy danych o możliwość wysyłania wiadomości do innych użytkowników w obrębie aplikacji lub na adres e-mail użytkownika,
2. Rozbudowa API o możliwość generowania raportów na podstawie wyników uczniów,
3. Rozbudowa Front-end:
   - logowanie użytkowników,
   - zarządaznie profilem,
   - utworzenie paneli dla nauczyciela i administratora,
   - utworzenie panelu forum,
   - kreator zadań oraz kreator odpowiedzi do zadań,
   - utworzenie panelu wiadomości,
   - obsługa powiadomień.
4. Testowanie,
5. Utworzenie dokumentacji całości projektu,
6. Wdrożenie.