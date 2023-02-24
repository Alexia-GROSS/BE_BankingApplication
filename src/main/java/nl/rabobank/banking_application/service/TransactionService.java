package nl.rabobank.banking_application.service;

import nl.rabobank.banking_application.dto.TransactionDto;
import nl.rabobank.banking_application.model.MessageResponse;
import nl.rabobank.banking_application.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface TransactionService {
    MessageResponse createTransaction(TransactionDto transactionDto);
    TransactionDto getASingleTransaction(Long transactionID);
    List<TransactionDto> getAllTransaction();
    MessageResponse updateTransaction(TransactionDto transactionDto);
    void deleteTransaction(Long transactionID);

}
