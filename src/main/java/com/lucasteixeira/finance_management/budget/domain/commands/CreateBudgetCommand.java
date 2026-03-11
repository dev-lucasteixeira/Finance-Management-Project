package com.lucasteixeira.finance_management.budget.domain.commands;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateBudgetCommand(
        UUID userId,
        String category,
        BigDecimal limitAmount
) {}
