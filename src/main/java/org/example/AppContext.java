package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import org.example.adapters.EnumAdapter;
import org.example.adapters.LocalDateAdapter;
import org.example.constants.Category;
import org.example.constants.Type;
import org.example.model.BudgetTransaction;
import org.example.repo.TransactionRepository;
import org.example.service.JsonTransactionRepo;
import org.joda.time.LocalDate;

import java.util.Properties;

@Getter
@Setter
public class AppContext {

    private Properties config;
    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(Category.class, new EnumAdapter<>(Category.class))
            .registerTypeAdapter(Type.class, new EnumAdapter<>(Type.class))
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();
    private TransactionRepository transactionRepository;
    private BudgetTransaction budgetTransaction;
    private ConsoleUI consoleUI;
}
