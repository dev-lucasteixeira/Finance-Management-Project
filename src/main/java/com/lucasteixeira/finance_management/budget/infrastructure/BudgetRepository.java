package com.lucasteixeira.finance_management.budget.infrastructure;

import com.lucasteixeira.finance_management.budget.domain.Budget;

import java.util.Optional;
import java.util.UUID;

public interface BudgetRepository {
    Optional<Budget> findByCategory(String category);
    Optional<Budget> findByUserId(UUID id);
    void save(Budget budget);

    Optional<Budget> findByUserIdAndCategory(UUID userId, String category);
}
