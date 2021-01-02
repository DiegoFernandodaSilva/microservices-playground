package com.github.diegofernandodasilva.covid19tracker.security;

import com.github.diegofernandodasilva.covid19tracker.security.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class LoggedUserContextProvider implements ContextProvider<User> {

    @Override
    public User get() {
        return new User(getLoggedUser());
    }

    private String getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
