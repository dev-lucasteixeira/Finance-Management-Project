package com.lucasteixeira.finance_management.budget.application;

import com.lucasteixeira.finance_management.budget.domain.Budget;
import com.lucasteixeira.finance_management.budget.domain.commands.CreateBudgetCommand;
import com.lucasteixeira.finance_management.budget.domain.factory.BudgetFactory;
import com.lucasteixeira.finance_management.budget.infrastructure.BudgetRepository;
import jakarta.transaction.Transactional;



public class BudgetUseCase {

    private final BudgetRepository repository;
    private final BudgetFactory budgetFactory;

    public BudgetUseCase(BudgetRepository repository, BudgetFactory budgetFactory) {
        this.repository = repository;
        this.budgetFactory = budgetFactory;
    }

    @Transactional
    public void execute(CreateBudgetCommand command) {
        if (repository.findByUserIdAndCategory(command.userId(), command.category()).isPresent()) {
            throw new IllegalStateException("Budget already exists for category: " + command.category());
        }

        Budget budget = budgetFactory.create(
                command.category(),
                command.limitAmount()
        );

        repository.save(budget);
    }
}
