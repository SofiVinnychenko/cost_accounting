package org.example.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.AppContext;
import org.example.constants.ConstantsForConfig;
import org.example.entity.Transaction;
import org.example.repo.TransactionRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
public class JsonTransactionRepo implements TransactionRepository {
    @Setter
    private AppContext appContext;
    private String transactionDataPath;

    public void init() {
        Properties config = appContext.getConfig();
        transactionDataPath = config.getProperty(ConstantsForConfig.TRANSACTION_DATA_PATH, null);
        if (transactionDataPath == null) {
            throw new RuntimeException("Configuration with " + ConstantsForConfig.TRANSACTION_DATA_PATH + " was not found");
        }
    }

    public List<Transaction> loadTransaction() {
        Gson gson = appContext.getGson();
        
        try (FileReader fileReader = new FileReader(transactionDataPath)){
            Type type = new TypeToken<List<Transaction>>() {}.getType();
            List<Transaction> transactionList = gson.fromJson(new JsonReader(fileReader),type);
            return transactionList;
        } catch (IOException e) {
            logger.error("Error with loading transactions from json");
            return new ArrayList<>();
        }
    }

    public void saveTransaction(List<Transaction> transactionList) {
        Gson gson = appContext.getGson();

        try (FileWriter fileWriter = new FileWriter(transactionDataPath)){
            String json = gson.toJson(transactionList);
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            logger.error("Error with saving transactions to json");
        }
    }
}
