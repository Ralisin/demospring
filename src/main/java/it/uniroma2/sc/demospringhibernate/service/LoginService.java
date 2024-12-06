package it.uniroma2.sc.demospringhibernate.service;

import it.uniroma2.sc.demospringhibernate.enums.Role;
import it.uniroma2.sc.demospringhibernate.secutiry.TokenStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {
    private final Map<String, String> users = new HashMap<>();  // Mappa username -> password
    private final Map<String, Role> userRoles = new HashMap<>(); // Mappa username -> ruolo

    @Autowired
    private TokenStorage tokenStorage;

    public LoginService() {
        users.put("user1", "pwd1");
        users.put("user2", "pwd2");
        users.put("user3", "pwd3");

        userRoles.put("user1", Role.VETERINARIO);
        userRoles.put("user2", Role.CLIENTE);
        userRoles.put("user3", Role.CLIENTE);
    }

    public String authenticate(String username, String password) throws Exception {
        String storedPassword = users.get(username);

        if (storedPassword != null && storedPassword.equals(password)) {
            Role role = userRoles.get(username);
            String token = tokenStorage.generateToken(role);

            return token;
        } else {
            throw new Exception("Username o password errati");
        }
    }

}
