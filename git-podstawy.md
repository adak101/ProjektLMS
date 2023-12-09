# Podstawowe komendy `git`

## Założenia wstępne:

1. Nie dokonujemy żadnych zmian na branchu `master`,
2. Nazwa brancha `osoba/zakres-zmian`, gdzie osoba jest w formacie: pierwsza litera imienia + nazwisko: np: `bsurma/przygotowanie-projektu-spring`
3. Pushujemy brancha do origina i tworzymy pull request, można samemu zmergeować, albo, jeżeli nie jesteśmy pewni zmian, dać komuś do sprawdzenia i zmergowania, poprzez przypisanie odpowiedniej osoby jako assignee
4. Nowego brancha tworzymy na podstawie mastera (najpierw komenda 7, później 1)
5. Nie pushujemy brancha do origina jeżeli są jakieś błędy, które powodują, że aplikacja nie działa
6. Treść wiadomości commita jest obowiązkowa i w skrócie powinna określać zmiany jakie zostały zrobione
7. Commity powinny być dzielone logicznie tak, aby dotyczyły jednego tematu


## Komendy

1. `git checkout -b <nazwa_brancha>` - tworzy i przełącza na nowy branch
2. `git add --all` - dodaje do indeksu wszystkie zmienione/nowo utworzone pliki
3. `git add <nazwa_pliku>` - dodaje do indeksu konkretny 
4. `git commit -m "<tresc>"` - tworzy commit z wiadomoscia
5. `git status` - sprawdza stan indeksu
6. `git push --set-upstream origin <nazwa_brancha>` - pushuje branch do origina
7. `git checkout master` - zmienia branch na master
8. `git pull --rebase` - pobiera zmiany z origin z zachowaniem commitow
9. `git log` - pokazuje historie commitow 