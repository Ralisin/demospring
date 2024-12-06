package it.uniroma2.sc.demospringhibernate.secutiry;

import it.uniroma2.sc.demospringhibernate.enums.Role;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenStorage {
    // Map UserId to his Role
    private ConcurrentHashMap<String, Role> tokenMap = new ConcurrentHashMap<>();

    public String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public void storeToken(String token, Role role) {
        tokenMap.put(token, role);
    }

    public Role getRoleByToken(String token) throws Exception {
        Role role = tokenMap.get(token);

        if (role == null) throw new Exception("Token not found");

        return tokenMap.get(token);
    }
}
