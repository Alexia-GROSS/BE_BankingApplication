package nl.rabobank.banking_application.repository;

import nl.rabobank.banking_application.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    //@Query("SELECT Transaction.transactionID, Transaction.amount, Transaction.targetAccount, Transaction.sendingAccount, Transaction.date, Transaction.description, Transaction.currency, Category.type FROM Category, Transaction WHERE Category.id = Transaction.category")
    //public List<Transaction> getAllTransactionWithCategory();
}
