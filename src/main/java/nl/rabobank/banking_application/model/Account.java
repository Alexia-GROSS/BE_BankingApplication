package nl.rabobank.banking_application.model;

import java.math.BigDecimal;

public class Account {
    private String iban;
    private BigDecimal balance;
    private int ownerID;

    public Account(String iban, BigDecimal balance, int ownerID) {
        this.iban = iban;
        this.balance = balance;
        this.ownerID = ownerID;
    }
}
