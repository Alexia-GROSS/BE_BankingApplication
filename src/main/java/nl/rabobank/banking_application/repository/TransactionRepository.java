package nl.rabobank.banking_application.repository;
import nl.rabobank.banking_application.model.Category;
import nl.rabobank.banking_application.model.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository  {
    void save(Transaction newTransaction);
    Optional<Transaction> findById(Long transactionID);

    List<Transaction> findAll();

    void deleteById(Long transactionID);

    List<Transaction> findAllByUsername(String username);

    List<Transaction> findTransactionsByCategory(Long categoryId);

    List<Transaction> findTransactionsByCategoryAndMonth(long id, int month);

    int calculateSumOfMoneyOfAllTransactions(String username);

    int retrieveMoneyInAccount(String username);
}
