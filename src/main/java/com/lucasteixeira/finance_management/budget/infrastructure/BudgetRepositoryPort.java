package com.lucasteixeira.finance_management.budget.infrastructure;

import com.lucasteixeira.finance_management.budget.domain.Budget;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BudgetRepositoryPort {

    Optional<Budget> findById(UUID id);

    Optional<Budget> findByUserId(UUID userId);

    Optional<Budget> findByUserIdAndCategory(UUID userId, String category);

    List<Budget> findAllByUserId(UUID userId);

    void save(Budget budget);

    void deleteById(UUID id);
}
