package com.lucasteixeira.finance_management.identity.domain.factory;

import com.lucasteixeira.finance_management.identity.domain.Identity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdentityFactory {

    public Identity createUser(String name, String cpf, String email, String password, String phone) {
        return new Identity(
                UUID.randomUUID(),
                name,
                cpf,
                email,
                password,
                phone
        );
    }
}
