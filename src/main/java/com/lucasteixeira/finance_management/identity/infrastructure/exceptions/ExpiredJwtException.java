package com.lucasteixeira.finance_management.identity.infrastructure.exceptions;

public class ExpiredJwtException extends RuntimeException {
    public ExpiredJwtException(String message) {
        super(message);
    }
}
