package com.lucasteixeira.finance_management.budget.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
interface BudgetRepository extends JpaRepository<BudgetEntity, UUID> {

    Optional<BudgetEntity> findByUserId(UUID userId);

    Optional<BudgetEntity> findByUserIdAndCategory(UUID userId, String category);

    List<BudgetEntity> findAllByUserId(UUID userId);
}
