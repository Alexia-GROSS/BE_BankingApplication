package nl.rabobank.banking_application.service;

import nl.rabobank.banking_application.dto.TransactionDto;
import nl.rabobank.banking_application.exception.ResourceNotFoundException;
import nl.rabobank.banking_application.model.Category;
import nl.rabobank.banking_application.model.MessageResponse;
import nl.rabobank.banking_application.model.Transaction;
import nl.rabobank.banking_application.repository.CategoryRepository;
import nl.rabobank.banking_application.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CategoryRepository categoryRepository;

/*
    static final String QUERY_VALUES =
            "SELECT Transaction.transactionID, Transaction.amount, Transaction.targetAccount, Transaction.sendingAccount, Transaction.date, Transaction.description, Transaction.currency, Category.type FROM Category, Transaction WHERE Category.id = Transaction.category";
*/

    public MessageResponse createTransaction(TransactionDto transactionDto) {
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDto.getAmount());
        newTransaction.setTargetAccount(transactionDto.getTargetAccount());
        newTransaction.setSendingAccount(transactionDto.getSendingAccount());
        newTransaction.setDate(transactionDto.getDate());
        newTransaction.setDescription(transactionDto.getDescription());
        newTransaction.setCurrency(transactionDto.getCurrency());
        Category newCategory = categoryRepository.getOne(transactionDto.getCategoryId());
        newTransaction.setCategory(newCategory);
        transactionRepository.save(newTransaction);
        return new MessageResponse("New Transaction created successfully");
    }

    public TransactionDto getASingleTransaction(Long transactionID) throws ResourceNotFoundException {
        Transaction transaction = transactionRepository.findById(transactionID).orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", transactionID));
        return toDto(transaction);
    }

    private TransactionDto toDto(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionID(transaction.getTransactionID());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setTargetAccount(transaction.getTargetAccount());
        transactionDto.setSendingAccount(transaction.getSendingAccount());
        transactionDto.setDate(transaction.getDate());
        transactionDto.setDescription(transaction.getDescription());
        transactionDto.setCurrency(transaction.getCurrency());
        transactionDto.setCategoryId(transaction.getCategory().getId());
        transactionDto.setCategoryType(transaction.getCategory().getType());
        return transactionDto;
    }

    private Transaction DtoToTransaction(TransactionDto transactionDto){
        Transaction transaction = new Transaction();
        transaction.setTransactionID(transactionDto.getTransactionID());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTargetAccount(transactionDto.getTargetAccount());
        transaction.setSendingAccount(transactionDto.getSendingAccount());
        transaction.setDate(transactionDto.getDate());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setCurrency(transactionDto.getCurrency());
        transaction.getCategory().setId(transactionDto.getCategoryId());
        transaction.getCategory().setType(transactionDto.getCategoryType());
        return transaction;
    }

    public List<TransactionDto> getAllTransaction() {
        //List<Transaction> transactionListResult = transactionRepository.findAll();
        return transactionRepository.findAll().stream().map(transaction -> toDto(transaction)).collect(Collectors.toList());
        /*System.out.println(transactionRepository);
        return transactionRepository.findAll();*/
        /*Query q = entityManager.createNativeQuery(QUERY_VALUES, String.class);
        return q.getResultList();*/
    }

    public void updateTransaction(Long transactionID, TransactionDto transactionDto)  throws ResourceNotFoundException{
        Optional<Transaction> updateTransaction = transactionRepository.findById(transactionID);
        if (!updateTransaction.isPresent()){
            throw new ResourceNotFoundException("Transaction", "id", transactionID);
        }
        else {
            updateTransaction.get().setAmount(transactionDto.getAmount());
            updateTransaction.get().setTargetAccount(transactionDto.getTargetAccount());
            updateTransaction.get().setSendingAccount(transactionDto.getSendingAccount());
            updateTransaction.get().setDate(transactionDto.getDate());
            updateTransaction.get().setDescription(transactionDto.getDescription());
            updateTransaction.get().setCurrency(transactionDto.getCurrency());
            Category newCategory = categoryRepository.getOne(transactionDto.getCategoryId());
            updateTransaction.get().setCategory(newCategory);
            transactionRepository.save(updateTransaction.get());
        }
    }

    public void deleteTransaction(Long transactionID) throws ResourceNotFoundException {
        Optional<Transaction> deleteTransaction = transactionRepository.findById(transactionID);
        if(!deleteTransaction.isPresent()){
            throw new ResourceNotFoundException("Transaction", "id", transactionID);
        }
        else {
            transactionRepository.deleteById(transactionID);
        }
    }


}
