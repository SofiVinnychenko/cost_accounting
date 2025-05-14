package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.constants.Category;
import org.example.constants.Type;
import org.joda.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Transaction {
    private int id;
    private Type type;
    private Category category;
    private double amount;
    private LocalDate date;
}
