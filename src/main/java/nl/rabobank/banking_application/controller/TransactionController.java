package nl.rabobank.banking_application.controller;

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
    public ResponseEntity<List<Transaction>> getAllTransaction () {
        List<Transaction> transactions = transactionService.getAllTransaction();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/find/{transactionID}")
    public ResponseEntity<Transaction> getTransactionById (@PathVariable("transactionID") Long transactionID) {
        Transaction transaction = transactionService.getASingleTransaction(transactionID);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addTransaction(@RequestBody Transaction transaction) {
        MessageResponse newTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }

    @PutMapping("/update/{transactionID}")
    public Optional<Transaction> updateTransaction( @PathVariable long transactionID, @RequestBody Transaction transaction) {
         return transactionService.updateTransaction(transactionID, transaction);
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
