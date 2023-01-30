package nl.rabobank.banking_application.service;

import nl.rabobank.banking_application.exception.ResourceNotFoundException;
import nl.rabobank.banking_application.model.MessageResponse;
import nl.rabobank.banking_application.model.Transaction;
import nl.rabobank.banking_application.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    TransactionRepository transactionRepository;

    public MessageResponse createTransaction(Transaction transaction) {
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.getAmount());
        newTransaction.setTargetAccount(transaction.getTargetAccount());
        newTransaction.setSendingAccount(transaction.getSendingAccount());
        newTransaction.setDate(transaction.getDate());
        newTransaction.setDescription(transaction.getDescription());
        newTransaction.setCurrency(transaction.getCurrency());
        transactionRepository.save(newTransaction);
        return new MessageResponse("New Transaction created successfully");
    }

    public Transaction getASingleTransaction(Long transactionID) throws ResourceNotFoundException {
        return transactionRepository.findById(transactionID).orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", transactionID));
    }

    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> updateTransaction(Long transactionID, Transaction transaction)  throws ResourceNotFoundException{
        Optional<Transaction> updateTransaction = transactionRepository.findById(transactionID);
        if (!updateTransaction.isPresent()){
            throw new ResourceNotFoundException("Transaction", "id", transactionID);
        }
        else {
            updateTransaction.get().setAmount(transaction.getAmount());
            updateTransaction.get().setTargetAccount(transaction.getTargetAccount());
            updateTransaction.get().setSendingAccount(transaction.getSendingAccount());
            updateTransaction.get().setDate(transaction.getDate());
            updateTransaction.get().setDescription(transaction.getDescription());
            updateTransaction.get().setCurrency(transaction.getCurrency());
            updateTransaction.get().setCategory(transaction.getCategory());
            transactionRepository.save(updateTransaction.get());
            return updateTransaction;
        }
    }

    public Optional<Transaction> deleteTransaction(Long transactionID) throws ResourceNotFoundException {
        Optional<Transaction> deleteTransaction = transactionRepository.findById(transactionID);
        if(!deleteTransaction.isPresent()){
            throw new ResourceNotFoundException("Transaction", "id", transactionID);
        }
        else {
            transactionRepository.deleteById(transactionID);
            return deleteTransaction;
        }
    }
}
