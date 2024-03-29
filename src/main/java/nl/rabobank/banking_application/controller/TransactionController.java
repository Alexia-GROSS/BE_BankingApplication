package nl.rabobank.banking_application.controller;

import nl.rabobank.banking_application.dto.TransactionDto;
import nl.rabobank.banking_application.model.MessageResponse;
import nl.rabobank.banking_application.model.Transaction;
import nl.rabobank.banking_application.repository.TransactionRepository;
import nl.rabobank.banking_application.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/all")
    public ResponseEntity<List<TransactionDto>> getAllTransaction (Principal principal) {
        String username = principal.getName();
        System.out.println(principal);
        List<TransactionDto> transactionDto = transactionService.getAllTransactionByUsername(username);
        return new ResponseEntity<>(transactionDto, HttpStatus.OK);
    }

    @GetMapping("/find/{transactionID}")
    public ResponseEntity<TransactionDto> getTransactionById (@PathVariable("transactionID") Long transactionID) {
        TransactionDto transactionDto = transactionService.getASingleTransaction(transactionID);
        return new ResponseEntity<>(transactionDto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addTransaction(@RequestBody TransactionDto transactionDto) {
        MessageResponse newTransaction = transactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> updateTransaction(@RequestBody TransactionDto transactionDto) {
        MessageResponse updatedTransaction = transactionService.updateTransaction(transactionDto);
         return new ResponseEntity<>(updatedTransaction, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{transactionID}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("transactionID") long transactionID) {
        transactionService.deleteTransaction(transactionID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*@Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        try{
            List<Transaction> transactions = new ArrayList<Transaction>();
            transactionRepository.findAll().forEach(transactions::add);

            if (transactions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/transactions/{transactionid}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("transactionid") long id ) {
        Optional<Transaction> transactionData = transactionRepository.findById(id);
        if (transactionData.isPresent()) {
            return new ResponseEntity<>(transactionData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        try {
            Transaction newTransaction = transactionRepository
                    .save(new Transaction(transaction.getAmount(), transaction.getTargetAccount(), transaction.getSendingAccount(), transaction.getDate(), transaction.getDescription(), transaction.getCurrency()));
            return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            transactionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/transactions/{sendingAccount}", method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> findBySendingAccount(@PathVariable("sendingAccount") String sendingAccount) {
        try {
            List<Transaction> transactions = transactionRepository.findBySendingAccount(sendingAccount);

            if (transactions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

}
