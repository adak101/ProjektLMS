package pl.samba.lms.utils.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pl.samba.lms.uzytkownicy.api.UzytkownikModel;

/**
 * Interfejs kontrolera REST API, obsługującego operacje na zasobach danego typu.
 *
 * @param <T> Typ obiektu DTO (Data Transfer Object) używany w interfejsie.
 * @param <K> Model obiektu, który jest wynikiem operacji w kontrolerze.
 * @author bsurma
 */
public interface ControllerInterface<T, K> {

    /**
     * Metoda zwracająca wszystkie możliwe punkty końcowe (endpointy) dostępne w danym kontrolerze.
     *
     * @return Kolekcja modelu zawierająca informacje o endpointach.
     */
    CollectionModel<Object> getAllEndPoints();

    /**
     * Metoda do pobierania zasobów z uwzględnieniem parametrów takich jak sortowanie, rozmiar strony itp.
     *
     * @param size      Liczba wyników na stronie.
     * @param page      Numer strony, liczony od 0.
     * @return Odpowiedź HTTP zawierająca kolekcję modelu zasobów.
     */
    public ResponseEntity<CollectionModel<K>> get(
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer page
    );

    /**
     * Metoda do pobierania pojedynczego zasobu na podstawie jego identyfikatora.
     *
     * @param id Identyfikator zasobu.
     * @return Odpowiedź HTTP zawierająca model zasobu o określonym identyfikatorze.
     */
    public ResponseEntity<K> get(@PathVariable("id") Integer id);
    /**
     * Metoda do usuwania zasobu na podstawie jego identyfikatora.
     *
     * @param id Identyfikator zasobu do usunięcia.
     */
    public void delete(@PathVariable("id") Integer id);

    /**
     * Metoda do dodawania nowego zasobu.
     *
     * @param data Dane zasobu do dodania.
     * @return Odpowiedź HTTP zawierająca dodany zasób.
     */
    public ResponseEntity<Object> post(@RequestBody T data);

    /**
     * Metoda do częściowej aktualizacji zasobu na podstawie identyfikatora i dostarczonych danych.
     *
     * @param id   Identyfikator zasobu do aktualizacji.
     * @param data Dane zasobu do częściowej aktualizacji.
     * @return Zaktualizowany zasób.
     */
    public ResponseEntity<Object> patch(
            @PathVariable("id") Integer id,
            @RequestBody T data);
}
