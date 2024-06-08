package com.cinemareservation.security;

import com.cinemareservation.model.Pracownik;
import com.cinemareservation.repository.PracownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PracownikRepository pracownikRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsService(PracownikRepository pracownikRepository, PasswordEncoder passwordEncoder) {
        this.pracownikRepository = pracownikRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Pracownik pracownik = pracownikRepository.findByEmail(username);
        if (pracownik == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.withUsername(pracownik.getEmail())
                .password(pracownik.getHaslo())
                .roles("USER")
                .build();
    }
}
