package nl.rabobank.banking_application.repository;
import nl.rabobank.banking_application.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository  {
    void save(Transaction newTransaction);
    Optional<Transaction> findById(Long transactionID);

    List<Transaction> findAll();

    void deleteById(Long transactionID);
}
