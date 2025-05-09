package github.devluiss.libraryapi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDatailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}