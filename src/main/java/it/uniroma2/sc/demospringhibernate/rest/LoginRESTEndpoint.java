package it.uniroma2.sc.demospringhibernate.rest;

import it.uniroma2.sc.demospringhibernate.dto.LoginDTO;
import it.uniroma2.sc.demospringhibernate.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
@RestController
@RequestMapping()
public class LoginRESTEndpoint {
    @Autowired
    private LoginService loginService;

    /**
     * Handles login form submission.
     *
     * @param loginDTO Login Data Transfer Object with username and password
     * @return ResponseEntity<> object with token to use for future operations
     */
    @PostMapping("/loginWithToken")
    public ResponseEntity<?> handleLoginAndGenToken(
            @RequestBody LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        try {
            String token = loginService.authenticate(username, password);

            return new ResponseEntity<>(Collections.singletonMap("token", token), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}
