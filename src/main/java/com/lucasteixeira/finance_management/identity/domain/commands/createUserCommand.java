package com.lucasteixeira.finance_management.identity.domain.commands;

public record createUserCommand(String name, String cpf, String email, String password, String phone) {
}
