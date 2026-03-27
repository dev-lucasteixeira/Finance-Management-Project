package com.lucasteixeira.finance_management.identity.domain;

import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Identity {

    private UUID  id;
    private String name;
    private String cpf;
    private String email;
    private String password;
    private String phone;
    private Access access;
    private LocalDateTime createdAt;

    public Identity(UUID id, String name, String cpf, String email, String password, String phone) {
        this(id, name, cpf, email, password, phone, Access.USER, LocalDateTime.now());
    }

    Identity(UUID id, String name, String cpf, String email, String password, String phone, Access access, LocalDateTime  createdAt) {

        Assert.notNull(id, "id must not be null");
        Assert.hasText(name, "name must not be empty");
        Assert.hasText(cpf, "cpf must not be empty");
        Assert.hasText(email, "email must not be empty");
        Assert.hasText(password, "password must not be empty");
        Assert.notNull(createdAt, "createdAt must not be null");

        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.access = access;
        this.createdAt = createdAt;
    }

    public static Identity restore(UUID id, String name, String cpf, String email, String password, String phone, Access access, LocalDateTime createdAt) {
        return new Identity(id, name, cpf, email, password, phone, access, createdAt);
    }
}
