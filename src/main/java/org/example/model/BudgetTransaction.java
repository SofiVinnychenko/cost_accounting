package org.example.model;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.AppContext;
import org.example.constants.Category;
import org.example.constants.Type;
import org.example.entity.Transaction;
import org.example.repo.TransactionRepository;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor
public class BudgetTransaction {
    @Setter
    private AppContext appContext;
    private TransactionRepository repository;
    private List<Transaction> transactionList;
    private int nextId;

    public void init() {
        this.repository = appContext.getTransactionRepository();
        if (repository == null) {
            logger.warn("TransactionRepository not found in AppContext");
        }
        this.transactionList = new ArrayList<>(repository.loadTransaction());
        this.nextId = transactionList.stream().mapToInt(Transaction::getId).max().orElse(0) + 1;
    }

    public void addTransaction(Type type, Category category, double amount) {
        Transaction transaction = new Transaction(nextId++, type, category, amount, LocalDate.now());
        transactionList.add(transaction);
        repository.saveTransaction(transactionList);
    }

    public List<Transaction> getAll() {
        return new ArrayList<>(transactionList);

    }

    public double getBalance() {
        return transactionList
                .stream()
                .mapToDouble(balance -> balance.getType().equals(Type.INCOME) ? balance.getAmount() : -balance.getAmount())
                .sum();
    }

    public List<Transaction> getTransactionByCategory(Category category) {
        List<Transaction> transactions = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            if (transaction.getCategory().equals(category)) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    public List<Transaction> getTransactionByType(Type type) {
        return transactionList
                .stream()
                .filter(transaction -> transaction.getType().equals(type))
                .collect(Collectors.toList());
    }

    public List<Transaction> sortByData() {
        return transactionList
                .stream()
                .sorted(Comparator.comparing(Transaction::getDate).reversed())
                .toList();
    }
}
