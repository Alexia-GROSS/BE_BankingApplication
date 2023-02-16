package nl.rabobank.banking_application.service;

import nl.rabobank.banking_application.model.MessageResponse;
import nl.rabobank.banking_application.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface TransactionService {
    MessageResponse createTransaction(Transaction transaction);
    Transaction getASingleTransaction(Long transactionID);
    List<Transaction> getAllTransaction();
    Optional<Transaction> updateTransaction(Long transactionID, Transaction transaction);
    Optional<Transaction> deleteTransaction(Long transactionID);

}
