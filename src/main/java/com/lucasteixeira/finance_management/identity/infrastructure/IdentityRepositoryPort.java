package com.lucasteixeira.finance_management.identity.infrastructure;

import com.lucasteixeira.finance_management.identity.domain.Access;
import com.lucasteixeira.finance_management.identity.domain.Identity;

import java.util.List;
import java.util.Optional;

public interface IdentityRepositoryPort{
    Optional<Identity> findByEmail(String email);

    Optional<Identity> findByCpf(String cpf);

    List<Identity> findAll();
    
    IdentityEntity save(Identity identity);

    void delete(Identity identity);
}
