This project is a console Java application for personal finance tracking.
Data is stored in JSON format and processed using an architecture that separates logic, data, and interface.

Main functionality of the program:
- viewing all transactions;
- viewing transactions by category or type (income/expense);
- viewing the total balance;
- sorting transactions by date: from newest to oldest;
- adding a new transaction.
Data storage:
Data is stored in a local JSON file.
Each transaction has fields: id, date, type (income/expense), category (enrollment/services/transfers/products/enrollment), amount.
The type and category fields are organized as a set of Enum constants.
Interface:
The interface is implemented as a console menu.

Features:
- An adapter for LocalDate has been implemented, allowing convenient storage and reading of data from JSON, as well as an adapter for deserializing Enum fields from a JSON file (if they are not specified in uppercase in the JSON file).
- The architecture provides for separation of responsibilities between modules, making testing and future changes easier.
- The Lombok library is used to reduce the amount of code, the Joda-Time library for date interaction, custom logging is configured using logback.xml to record logs in a separate file, implemented using the @Slf4j annotation from the Lombok library.
- For loading JSON, a configuration file is used, which specifies the path to the data.
