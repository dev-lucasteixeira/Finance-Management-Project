package com.lucasteixeira.finance_management.budget.infrastructure;

import com.lucasteixeira.finance_management.budget.domain.Budget;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "budgets")
@Getter
class BudgetEntity {

    @Id
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String category;

    @Column(name = "limit_amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal limitAmount;

    @Column(name = "current_spending", nullable = false, precision = 19, scale = 2)
    private BigDecimal currentSpending;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    BudgetEntity() {
    }

    BudgetEntity(UUID id, UUID userId, String category, BigDecimal limitAmount,
                 BigDecimal currentSpending, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.category = category;
        this.limitAmount = limitAmount;
        this.currentSpending = currentSpending;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    Budget toDomain() {
        return Budget.restore(
                id,
                userId,
                category,
                limitAmount,
                currentSpending,
                createdAt,
                updatedAt
        );
    }

    static BudgetEntity fromDomain(Budget budget) {
        return new BudgetEntity(
                budget.getId(),
                budget.getUserId(),
                budget.getCategory(),
                budget.getLimitAmount(),
                budget.getCurrentSpending(),
                budget.getCreatedAt(),
                budget.getUpdatedAt()
        );
    }
}
