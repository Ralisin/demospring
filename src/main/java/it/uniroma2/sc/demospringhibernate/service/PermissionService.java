package it.uniroma2.sc.demospringhibernate.service;

import it.uniroma2.sc.demospringhibernate.enums.Role;
import it.uniroma2.sc.demospringhibernate.secutiry.TokenStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class PermissionService {
    @Autowired
    private TokenStorage tokenStorage;

    public boolean hasPermissionToDelete(String token) {
        Role role;

        try {
            // Check token
            role = tokenStorage.getRoleByToken(token);
        } catch (Exception e) {
            Logger.getAnonymousLogger().info(e.getMessage());
            return false;
        }

        return role == Role.VETERINARIO;
    }
}
