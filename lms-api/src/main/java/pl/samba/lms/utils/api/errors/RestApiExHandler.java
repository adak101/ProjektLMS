package pl.samba.lms.utils.api.errors;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.samba.lms.utils.http.HttpResponse;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestApiExHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        HttpResponse response = new HttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Wystąpił błąd.", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(Exception ex, WebRequest request) {
        HttpResponse response = new HttpResponse(HttpStatus.NOT_FOUND, "Wystąpił błąd braku dancyh.", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(Exception ex, WebRequest request) {
        HttpResponse response = new HttpResponse(HttpStatus.NOT_FOUND, "Błędny login lub hasło.", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> handleDuplicateKeyException(Exception ex, WebRequest request) {
        HttpResponse response = new HttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Wystąpił błąd powtórzenia danych dla klucza unikalnego.", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
