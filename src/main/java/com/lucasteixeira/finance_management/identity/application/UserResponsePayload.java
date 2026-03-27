package com.lucasteixeira.finance_management.identity.application;

import com.lucasteixeira.finance_management.identity.domain.Access;

import java.time.LocalDateTime;

public record UserResponsePayload(
        String nome,
        String email,
        String cpf,
        String phone,
        Access access,
        LocalDateTime createdAt
) {
}
