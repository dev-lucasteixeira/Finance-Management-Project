package com.lucasteixeira.finance_management.budget.domain;

import lombok.Getter;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Budget {

    private final UUID id;
    private final UUID userId;
    private final String category;
    private BigDecimal limitAmount;
    private BigDecimal currentSpending;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Budget(UUID id, UUID userId, String category, BigDecimal limitAmount) {
        this(id, userId, category, limitAmount, BigDecimal.ZERO, LocalDateTime.now(), LocalDateTime.now());
    }

    Budget(UUID id, UUID userId, String category, BigDecimal limitAmount, BigDecimal currentSpending, LocalDateTime createdAt, LocalDateTime updatedAt) {
        Assert.notNull(id, "ID cannot be null");
        Assert.notNull(userId, "User ID cannot be null");
        Assert.hasText(category, "Category cannot be empty");
        Assert.notNull(limitAmount, "Limit cannot be null");
        Assert.notNull(currentSpending, "Spending cannot be null");
        Assert.notNull(createdAt, "Created at cannot be null");
        Assert.notNull(updatedAt, "Updated at cannot be null");

        this.id = id;
        this.userId = userId;
        this.category = category;
        this.limitAmount = limitAmount;
        this.currentSpending = currentSpending;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Budget restore(UUID id, UUID userId, String category, BigDecimal limitAmount, BigDecimal currentSpending, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Budget(id, userId, category, limitAmount, currentSpending, createdAt, updatedAt);
    }

    public void addSpending(BigDecimal amount) {
        Assert.isTrue(amount.compareTo(BigDecimal.ZERO) >= 0, "Cannot add negative spending");
        this.currentSpending = this.currentSpending.add(amount);
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isExceeded() {
        return currentSpending.compareTo(limitAmount) > 0;
    }

    public void adjustLimit(BigDecimal newLimit) {
        Assert.isTrue(newLimit.compareTo(BigDecimal.ZERO) > 0, "New limit must be positive");
        this.limitAmount = newLimit;
        this.updatedAt = LocalDateTime.now();
    }
}
