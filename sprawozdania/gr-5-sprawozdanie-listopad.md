# Raport z postępu projektu *Systemu Zarządzania Nauką Online (LMS)*

na dzień: 30-11-2023 r.

## Założenia projektu

Opracowanie kompleksowego systemu zarządzania nauczaniem (LMS) dla szkół lub uniwersytetów do zarządzania kursami, zadaniami, ocenami i profilami użytkowników.

## Członkowie Zespołu

- Mikołaj Noga,
- Bartosz Surma,
- Szymon Świercz,
- Adam Waśko

## Wykonane prace

W tym miesiącu udało nam się stworzyć uproszczoną specyfikację wymagań, która wyznacza ścieżkę rozwoju projektu. Specyfikacja ta zawiera zarys finalnego produktu oraz określa główne funkcje systemu. Skupiliśmy się na precyzyjnym opisie roli użytkowników i funkcjonalności, które system będzie im umożliwiał.

Opracowaliśmy schemat bazy danych, który jest kluczowym elementem naszego systemu. Przyjęliśmy podejście do zarządzania danymi oparte na bazie danych MySQL. Zdecydowaliśmy, że w fazie rozwoju projektu będziemy używać wbudowanej bazy H2 dostępnej w frameworku Spring. Zaimplementowaliśmy również podstawowe procedury CRUD (Create, Read, Update, Delete), umożliwiające manipulację danymi w bazie.

## Plan prac na kolejny miesiąc

W natchodzącym miesiącu skupimy się głównie na implementacji projektu. Nasza strategia opiera się na stopniowym rozwijaniu funkcji, zaczynając od najbardziej podstawowych, aby umożliwić równoczesną pracę na warstwie back-end i front-end.

1. Warstwa Back-End:

   - Podział funkcji na moduły, umożliwiając niezależne rozwijanie poszczególnych części systemu.

   - Implementacja obsługi różnych rodzajów danych, takich jak profile użytkowników, przedmioty i zadania.

2. Warstwa Front-End:

   - Opracowanie modułowego podejścia do interfejsu, umożliwiającego łatwe dodawanie nowych funkcji.

   - Prace nad interfejsem aplikacji, koncentrując się na estetyce, użyteczności i responsywności.

3. Warstwa administracyjna:

   - Szukanie najlepszego rozwiązania do hostingu bazy danych oraz aplikacji,

## [Specyfikacja wymagań](/specyfikacja-wymagan/specyfikacja-wymagan-lms.md)