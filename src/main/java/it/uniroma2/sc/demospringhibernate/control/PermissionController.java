package it.uniroma2.sc.demospringhibernate.control;

import it.uniroma2.sc.demospringhibernate.enums.Role;
import it.uniroma2.sc.demospringhibernate.secutiry.TokenStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionController {
    @Autowired
    private TokenStorage tokenStorage;

    public boolean hasPermissionToDelete(String token) {
        Role role;

        try {
            // Check token
            role = tokenStorage.getRoleByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return role == Role.VETERINARIO;
    }
}
