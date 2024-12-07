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

    /**
     * To have the permission to delete a dog you must at least be registered ad a VETERINARIO
     * This method checks if the passed token is associated to a VETERINARIO
     *
     * @param token it is the {@code String} identifier sent to the user at the login
     * @return {@code true} if the token is associated to a role VETERINARIO
     */
    public boolean hasPermissionToDelete(String token) {
        Role role;

        try {
            // Check token
            role = tokenStorage.getRoleByToken(token);
        } catch (Exception e) {
            Logger.getAnonymousLogger().info(e.getMessage());
            return false;
        }

        // Only the VETERINARIO can delete a dog
        return role == Role.VETERINARIO;
    }

    /**
     * To have the permission to create a dog you must at least be registered ad a VETERINARIO
     * This method checks if the passed token is associated to a VETERINARIO
     *
     * @param token it is the {@code String} identifier sent to the user at the login
     * @return {@code true} if the token is associated to a role VETERINARIO
     */
    public boolean hasPermissionToCreate(String token) {
        Role role;

        try {
            role = tokenStorage.getRoleByToken(token);
        } catch (Exception e) {
            Logger.getAnonymousLogger().info(e.getMessage());
            return false;
        }

        // Only the VETERINARIO can create a dog
        return role == Role.VETERINARIO;
    }
}
