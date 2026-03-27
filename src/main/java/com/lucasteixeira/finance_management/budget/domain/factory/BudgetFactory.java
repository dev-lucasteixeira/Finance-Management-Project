package com.lucasteixeira.finance_management.budget.domain.factory;

import com.lucasteixeira.finance_management.budget.domain.Budget;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class BudgetFactory {

    public Budget create(UUID userId, String category, BigDecimal limitAmount) {
        return new Budget(
                UUID.randomUUID(),
                userId,
                category,
                limitAmount
        );
    }
}
