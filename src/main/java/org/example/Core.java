package org.example;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.model.BudgetTransaction;
import org.example.repo.TransactionRepository;
import org.example.service.JsonTransactionRepo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class Core {
    private Properties config;
    private ConsoleUI consoleUI;
    private BudgetTransaction budgetTransaction;
    private TransactionRepository transactionRepository;

    public void init(String filename) {
        try {
            config = new Properties();
            config.load(new FileReader(filename));

            AppContext appContext = new AppContext();
            appContext.setConfig(config);

            transactionRepository = new JsonTransactionRepo();
            appContext.setTransactionRepository(transactionRepository);
            transactionRepository.setAppContext(appContext);

            budgetTransaction = new BudgetTransaction();
            appContext.setBudgetTransaction(budgetTransaction);
            budgetTransaction.setAppContext(appContext);

            consoleUI = new ConsoleUI();
            appContext.setConsoleUI(consoleUI);
            consoleUI.setAppContext(appContext);

            // init
            transactionRepository.init();
            budgetTransaction.init();
            consoleUI.start();

        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }
}
