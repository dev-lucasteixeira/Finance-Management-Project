package com.lucasteixeira.finance_management.budget.infrastructure;

import com.lucasteixeira.finance_management.budget.domain.Budget;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class BudgetRepositoryAdapter implements BudgetRepositoryPort {

    private final BudgetRepository jpaRepository;

    @Override
    public Optional<Budget> findById(UUID id) {
        return jpaRepository.findById(id).map(BudgetEntity::toDomain);
    }

    @Override
    public Optional<Budget> findByUserId(UUID userId) {
        return jpaRepository.findByUserId(userId).map(BudgetEntity::toDomain);
    }

    @Override
    public Optional<Budget> findByUserIdAndCategory(UUID userId, String category) {
        return jpaRepository.findByUserIdAndCategory(userId, category).map(BudgetEntity::toDomain);
    }

    @Override
    public List<Budget> findAllByUserId(UUID userId) {
        return jpaRepository.findAllByUserId(userId).stream().map(BudgetEntity::toDomain).toList();
    }

    @Override
    public void save(Budget budget) {
        jpaRepository.save(BudgetEntity.fromDomain(budget));
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
