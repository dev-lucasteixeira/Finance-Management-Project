package com.lucasteixeira.finance_management.identity.infrastructure;


import com.lucasteixeira.finance_management.identity.domain.Access;
import com.lucasteixeira.finance_management.identity.domain.Identity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class IdentityRepositoryAdapter implements  IdentityRepositoryPort {
    
    private final IdentityRepository identityRepository;

    @Override
    public Optional<Identity> findByEmail(String email){return identityRepository.findByEmail(email).map(IdentityEntity::toDomain);}

    @Override
    public Optional<Identity> findByCpf(String cpf){return identityRepository.findByCpf(cpf).map(IdentityEntity::toDomain);}

    @Override
    public List<Identity> findAll(){return identityRepository.findAll().stream().map(IdentityEntity::toDomain).toList();}

    @Override
    public IdentityEntity save(Identity identity) {return identityRepository.save(IdentityEntity.fromDomain(identity));}

    @Override
    public void delete(Identity identity) {
        identityRepository.deleteByEmail(identity.getEmail());
    }
}
