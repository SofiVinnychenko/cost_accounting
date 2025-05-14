package org.example;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.constants.Category;
import org.example.constants.Type;
import org.example.entity.Transaction;
import org.example.model.BudgetTransaction;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Scanner;

@Slf4j
public class ConsoleUI {
    @Setter
    private AppContext appContext;
    private BudgetTransaction budgetTransaction;
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        this.budgetTransaction = appContext.getBudgetTransaction();
        while (true) {
            System.out.println("___INCOME/EXPENSE ACCOUNTING PROGRAM___");
            System.out.println("Switch number of the required action:");
            System.out.println("1. Get all transactions");
            System.out.println("2. Get transaction by category");
            System.out.println("3. Get balance");
            System.out.println("4. Get transaction by type");
            System.out.println("5. Sort transactions by date in descending order");
            System.out.println("6. Add new transaction");
            System.out.println("7. Close program");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> {List<Transaction> transactionList = budgetTransaction.getAll();
                    transactionList.forEach(System.out::println);
                }
                case "2" -> {System.out.println("Enter Category: ENTERTAINMENT, SERVICES, TRANSFERS, PRODUCTS:");
                String cat = scanner.nextLine().toUpperCase();
                    try {
                        Category category = Category.valueOf(cat);
                        List<Transaction> transactionList = budgetTransaction.getTransactionByCategory(category);
                        transactionList.forEach(System.out::println);
                    } catch (IllegalArgumentException e) {
                        logger.error("Invalid category entered");
                    }
                }
                case "3" -> System.out.println(budgetTransaction.getBalance());
                case "4" -> {System.out.println("Enter Type: INCOME, EXPENSE:");
                    String typ = scanner.nextLine().toUpperCase();
                    try {
                        Type type = Type.valueOf(typ);
                        List<Transaction> transactionList = budgetTransaction.getTransactionByType(type);
                        transactionList.forEach(System.out::println);
                    } catch (IllegalArgumentException e) {
                        logger.error("Invalid type entered");
                    }
                }
                case "5" -> {List<Transaction> transaction = budgetTransaction.sortByData();
                    transaction.forEach(System.out::println);
                }
                case "6" -> {
                    String categoryScanner;
                    System.out.println("Enter type: INCOME, EXPENSE:");
                    String typeScanner = scanner.nextLine().toUpperCase();
                    if (typeScanner.equals("INCOME")) {
                        System.out.println("Enter category: TRANSFERS, ENROLLMENT:");
                        categoryScanner = scanner.nextLine().toUpperCase();
                    } else {
                        System.out.println("Enter category: ENTERTAINMENT, SERVICES, TRANSFERS, PRODUCTS:");
                        categoryScanner = scanner.nextLine().toUpperCase();
                    }
                    System.out.println("Enter amount:");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    try {
                        Type type = Type.valueOf(typeScanner);
                        Category category = Category.valueOf(categoryScanner);
                        budgetTransaction.addTransaction(type, category, amount);
                        System.out.println("New transaction was created successfully!");
                    } catch (IllegalArgumentException e) {
                        logger.error("Invalid input type/category entered");
                    }
                }
                case "7" -> {
                    return;
                }
            }
        }
    }
}
