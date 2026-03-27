package com.lucasteixeira.finance_management.budget.application;

import com.lucasteixeira.finance_management.budget.domain.Budget;
import com.lucasteixeira.finance_management.budget.domain.commands.CreateBudgetCommand;
import com.lucasteixeira.finance_management.budget.domain.factory.BudgetFactory;
import com.lucasteixeira.finance_management.budget.infrastructure.BudgetRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.modulith.NamedInterface;

import java.util.List;
import java.util.UUID;


@NamedInterface("api")
public class BudgetUseCase {

    private final BudgetRepositoryPort repository;
    private final BudgetFactory budgetFactory;

    public BudgetUseCase(BudgetRepositoryPort repository, BudgetFactory budgetFactory) {
        this.repository = repository;
        this.budgetFactory = budgetFactory;
    }

    @Transactional
    public void execute(CreateBudgetCommand command) {
        if (repository.findByUserIdAndCategory(command.userId(), command.category()).isPresent()) {
            throw new IllegalStateException("Budget already exists for category: " + command.category());
        }

        Budget budget = budgetFactory.create(
                command.userId(),
                command.category(),
                command.limitAmount()
        );

        repository.save(budget);
    }

    @Transactional
    public void addSpending(UUID userId, String category, java.math.BigDecimal amount) {
        Budget budget = repository.findByUserIdAndCategory(userId, category)
                .orElseThrow(() -> new IllegalStateException("Budget not found for category: " + category));
        
        budget.addSpending(amount);
        repository.save(budget);
    }

    public List<Budget> getBudgetsByUser(UUID userId) {
        return repository.findAllByUserId(userId);
    }

    public Budget getBudget(UUID budgetId) {
        return repository.findById(budgetId)
                .orElseThrow(() -> new IllegalStateException("Budget not found"));
    }
}
