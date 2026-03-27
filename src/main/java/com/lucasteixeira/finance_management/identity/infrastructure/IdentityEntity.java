package com.lucasteixeira.finance_management.identity.infrastructure;

import com.lucasteixeira.finance_management.identity.domain.Access;
import com.lucasteixeira.finance_management.identity.domain.Identity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Table(name = "users")
@Entity
public class IdentityEntity {

    @Id
    private UUID id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "cpf", nullable = false)
    private String cpf;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "access", nullable = false)
    private Access access;
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    IdentityEntity() {

    }

    IdentityEntity(UUID id, String name, String cpf, String email, String password, String phone, Access access) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.access = access;
    }

    public IdentityEntity(UUID id, String name, String cpf, String email, String password, String phone, Access access, LocalDateTime createdAt) {
    }

    Identity toDomain() {
        return Identity.restore(id, name, cpf, email, password, phone, access, createAt);
    }

    static IdentityEntity fromDomain(Identity identity) {
        return new IdentityEntity(
                identity.getId(),
                identity.getName(),
                identity.getCpf(),
                identity.getEmail(),
                identity.getPassword(),
                identity.getPhone(),
                identity.getAccess(),
                identity.getCreatedAt()
        );
    }


}
