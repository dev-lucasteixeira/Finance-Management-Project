package com.lucasteixeira.finance_management.identity.infrastructure;

import com.lucasteixeira.finance_management.identity.domain.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IdentityRepository extends JpaRepository<IdentityEntity, UUID> {

    Optional<IdentityEntity> findByEmail(String email);

    Optional<IdentityEntity> findByCpf(String cpf);

    List<IdentityEntity> findAllByAccess(Access access);

    void deleteByEmail(String email);
}
