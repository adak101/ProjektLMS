package pl.samba.lms.security.auth;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.samba.lms.security.jwt.JwtService;
import pl.samba.lms.utils.http.LoginRequest;
import pl.samba.lms.utils.http.LoginResponse;
import pl.samba.lms.utils.http.HttpResponse;
import pl.samba.lms.uzytkownicy.uzytkownicy.Uzytkownik;
import pl.samba.lms.uzytkownicy.uzytkownicy.database.UzytkownikRepository;

@RestController
@RequestMapping(path="/api/v1/auth", produces = "application/json")
@AllArgsConstructor
public class AuthController {
    private final UzytkownikRepository dataSet;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Uzytkownik uzytkownik){
        Integer id = dataSet.save(uzytkownik);
        if(id != null){
            HttpResponse response = new HttpResponse(HttpStatus.CREATED,"Konto utworzone");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        else {
            HttpResponse response = new HttpResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Błąd utworzenia konta");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request){
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getLogin(),
                                    request.getHaslo())
                    );
            String login = authentication.getName();

            Uzytkownik uzytkownik = dataSet.getByUnique(login);
            String token = jwtUtil.createToken(uzytkownik);
            LoginResponse response = new LoginResponse(uzytkownik.getLogin(),token);

            return ResponseEntity.ok(response);

        }catch (BadCredentialsException e){
            HttpResponse response = new HttpResponse(HttpStatus.BAD_REQUEST,"Błędny login lub hasło");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            HttpResponse response = new HttpResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
