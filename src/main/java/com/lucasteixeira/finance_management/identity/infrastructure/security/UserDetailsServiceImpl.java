package com.lucasteixeira.finance_management.identity.infrastructure.security;

import com.lucasteixeira.finance_management.identity.domain.Identity;
import com.lucasteixeira.finance_management.identity.infrastructure.IdentityRepositoryPort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IdentityRepositoryPort repository;

    public UserDetailsServiceImpl(IdentityRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Identity identity = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        return org.springframework.security.core.userdetails.User
                .withUsername(identity.getEmail())
                .password(identity.getPassword())
                .roles(identity.getAccess().name())
                .build();
    }
}
