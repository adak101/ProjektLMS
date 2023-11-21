# Learning Menagement System (SAMBA-LMS)

## Cel i przedmiot projektu

Celem projektu jest stworzenie kompleksowego systemu zarządzania nauczaniem (LMS) dla szkół i uniwersytetów w celu zarządzania kursami, zadaniami, ocenami i profilami użytkowników. System będzie usprawniał proces nauczania i szkolenia poprzez zapewnienie uczniom łatwego dostępu do materiałów edukacyjnych (takich jak: prezentacje, podręczniki, nagrania wideo, itp.) oraz umożliwienie wykonywanie zadań, testów i sprawdzianów utworzonych przez nauczycieli. Podnadto system umożliwi śledzenie postępów uczestników oraz dostarczy raporty i analizy dotyczących wyników i zaangażowania uczniów. Celem systemu jest również ułatwienie komunikacji między uczestnikami i nauczycielami poprzez utworzenie forów dyskusyjnych oraz możliwości wysyłania wiadomości e-mail bezpośrednio z systemu.

## Założenia wstępne

Każdy użytkownik loguje się w systemie przy pomocy danych uwierzytelniających. Ma dostęp do strony głównej, na której znajduje się skrót do wszystkich istotnych informacji, z których może przejść do odpowiednich paneli, zakładek oraz funkcji systemu.

Korzystając z odpowiednich panel i zakładek **uczeń** ma możliwość rejestracji na przedmioty, przeglądania przedmiotów, których jest uczestnikiem, wraz z materiałami do nich dołączonymi, a także brać udział na forum dyskusyjnym oraz wykonywać zadania przypisane do danego przedmiotu. Ponad to może przeglądać kartę ocen, a także kontaktować się z innymi uczniami, nauczycielami oraz pracownikami administracyjnymi poprzez wiadomości e-mail.

**Nauczyciel**, korzystając z systemu, ma możliwość dodawania oraz etydowania panelu przedmiotu oraz dodawać i edytować materiały, zadania oraz testy przypisane do danego przedmiotu. Nauczyciel może generować i wyświetlać raporty, ukazujące zaangażowanie uczniów w dany przedmiot, a także wystwiać oceny cząstkowe (za wykonane zadania) oraz oceny końcowe.

**Administrator**, za pośrednictwem systemu, ma możliwość tworzenia kont dla uczniów, nauczycieli oraz innych pracowników administracyjnych, a także przedłużania ważności kont uczniów. System umożliwia mu także pomoc w zarządzaniu przedmiotem poprzez jego rejestracię po utworzeniu przez nauczyciela, a także dodawania i usuwania poszczególnych uczniów. Ponad to administrator może generować i wyświetlać raporty, ukazujące zaangażowanie uczniów w dany przedmiot oraz raporty ukazujące zaangażowanie nauczycieli.

## Funkjonalność systemu

### System z punktu widzenia ucznia

- Strona główna (skrót wszystkiego)
- Rejestracja lub dołączanie na podstawie kodu do/na przedmioty
- Na boku terminy zaliczenia prac
- Kursy mają osobne strony (na wzór Moodle)
- Panel przedmiotu składający się z:
  - Informacji o przedmiocie
  - Treść przedmiotu (PDF, prezentacje)
  - Zadania w ramach przedmiotu (procent wykonanych zadań)
  - Forum przedmiotu (osobne zakładki)
- Uczeń ma możliwość rejestracji na przedmioty
- Zarządzanie profilem (edycja danych)
- System wiadomości oparty na mailu
- Karta ocen (w podziale na semestry)
- Przegląd pracowników

### System z punktu widzenia nauczyciela

- Zarządzanie profilem (edycja danych)
- System wiadomości oparty na mailu
- Przegląd pracowników
- Panel przedmiotu składający się z:
  - Informacji o przedmiocie (może edytować)
  - Treść przedmiotu (PDF, prezentacje)
  - Zadania w ramach przedmiotu (może tworzyć, procent uczniów, którzy wykonali zadanie)
  - Forum przedmiotu (osobne zakładki)
    - Wyświetlanie powiadomień o terminach zadań
- Nauczyciel ma możliwość wystawiania ocen i ustalania terminów
- Rejestracja przedmiotu (ustala limity osób na przedmiocie)
- Ma możliwość usunięcia ucznia z przedmiotu
- Może wygenerować kod do przedmiotu (jak na Teams)
- Ma możliwość edytowania terminu zadania dla danego ucznia

### System z punktu widzenia administratora

- Zarządzanie profilem (edycja danych)
- System wiadomości oparty na mailu
- Przegląd pracowników
- Panel przedmiotu (do przeglądania)
- Admin ma możliwość tworzenia kont dla uczniów, nauczycieli i administratorów
- Potwierdza rejestracje przedmiotu
- Ma możliwość usunięcia i dodania ucznia do przedmiotu
- Ma możliwość deaktywacji konta jak i przedłużenia ważności konta
- Ma możliwość przeglądania i generowania raportów uczniów, nauczycieli, przedmiotu

## Diagram przypadków użycia

## Schemat bazy danych

![lms-db-schema](lms-db-schema.png)

## Schematy *front-end*