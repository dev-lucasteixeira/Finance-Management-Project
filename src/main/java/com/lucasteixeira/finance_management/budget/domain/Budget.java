package com.lucasteixeira.finance_management.budget.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class Budget {

    private final UUID id;
    private final String category;
    private BigDecimal limitAmount;
    private BigDecimal currentSpending;

    public Budget(UUID id, String category, BigDecimal limitAmount) {
        this(id, category, limitAmount, BigDecimal.ZERO);
    }

    Budget(UUID id, String category, BigDecimal limitAmount, BigDecimal currentSpending) {
        Assert.notNull(id, "ID cannot be null");
        Assert.hasText(category, "Category cannot be empty");
        Assert.notNull(limitAmount, "Limit cannot be null");
        Assert.notNull(currentSpending, "Spending cannot be null");

        this.id = id;
        this.category = category;
        this.limitAmount = limitAmount;
        this.currentSpending = currentSpending;
    }

    public void addSpending(BigDecimal amount) {
        Assert.isTrue(amount.compareTo(BigDecimal.ZERO) >= 0, "Cannot add negative spending");
        this.currentSpending = this.currentSpending.add(amount);
    }

    public boolean isExceeded() {
        return currentSpending.compareTo(limitAmount) > 0;
    }

    public void adjustLimit(BigDecimal newLimit) {
        Assert.isTrue(newLimit.compareTo(BigDecimal.ZERO) > 0, "New limit must be positive");
        this.limitAmount = newLimit;
    }
}
