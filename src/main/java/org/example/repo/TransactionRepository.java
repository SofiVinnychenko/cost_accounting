package org.example.repo;

import org.example.AppContext;
import org.example.entity.Transaction;

import java.util.List;

public interface TransactionRepository {
    void init();
    List<Transaction> loadTransaction();
    void saveTransaction(List<Transaction> transactionList);
    void setAppContext(AppContext context);
}
