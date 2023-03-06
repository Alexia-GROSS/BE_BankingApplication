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
        Category newCategory = categoryRepository.getOne(transactionDto.getCategoryId());
        transaction.setCategory(newCategory);
        /*transaction.getCategory().setId(transactionDto.getCategoryId());
        transaction.getCategory().setType(transactionDto.getCategoryType());*/
        return transaction;
    }

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

    public List<TransactionDto> getAllTransaction() {
        return transactionRepository.findAll().stream().map(transaction -> toDto(transaction)).collect(Collectors.toList());
    }

    public MessageResponse updateTransaction(TransactionDto transactionDto)  throws ResourceNotFoundException{
        Transaction transaction = transactionRepository.findById(transactionDto.getTransactionID()).orElseThrow(() -> new ResourceNotFoundException("Transaction", "id", transactionDto.getTransactionID()));
        transactionRepository.save(DtoToTransaction(transactionDto));
        return new MessageResponse("Transaction updated successfully");
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
