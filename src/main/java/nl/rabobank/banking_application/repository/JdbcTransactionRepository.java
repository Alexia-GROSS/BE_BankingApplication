package nl.rabobank.banking_application.repository;

import nl.rabobank.banking_application.model.Category;
import nl.rabobank.banking_application.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTransactionRepository implements TransactionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Transaction newTransaction) {
        jdbcTemplate.update("INSERT INTO transactions (amount, targetaccount, sendingaccount, date, description, currency, category) VALUES(?,?,?,?,?,?,?)",
                newTransaction.getAmount(), newTransaction.getTargetAccount(), newTransaction.getSendingAccount(), newTransaction.getDate(), newTransaction.getDescription(), newTransaction.getCurrency(), newTransaction.getCategory().getId());
    }

    @Override
    public Optional<Transaction> findById(Long transactionID) {
        Transaction transactionById = jdbcTemplate.queryForObject("SELECT * FROM transactions, categories WHERE transactionid=? AND transactions.category = categories.id",
                new Object[]{transactionID}, new TransactionMapper());
        assert transactionById != null;
        return Optional.of(
                transactionById
            );
    }

    @Override
    public List<Transaction> findAll() {
        return jdbcTemplate.query("SELECT * from transactions, categories\n" +
                " WHERE transactions.category = categories.id ORDER BY transactions.date ASC", new TransactionMapper());
    }

    @Override
    public List<Transaction> findAllByUsername(String username) {
        String sql = "SELECT * from clients, transactions, categories, accounts" +
                " WHERE clients.username=? AND clients.clientid = accounts.ownerid" +
                " AND (accounts.iban = transactions.targetaccount OR accounts.iban = transactions.sendingaccount)" +
                " AND transactions.category = categories.id ORDER BY transactions.date ASC";
        return jdbcTemplate.query(sql, new Object[]{username}, new TransactionMapper());
    }

    @Override
    public List<Transaction> findTransactionsByCategory(Long categoryId) {
        String sql = "SELECT * FROM transactions, categories WHERE transactions.category=? AND transactions.category = categories.id";
        return jdbcTemplate.query(sql, new Object[]{categoryId}, new TransactionMapper());
    }

    @Override
    public List<Transaction> findTransactionsByCategoryAndMonth(long categoryId, int month) {
        String sql = "SELECT * FROM transactions, categories WHERE transactions.category=? AND transactions.category = categories.id AND extract(month from transactions.date) = ?";
        return jdbcTemplate.query(sql, new Object[]{categoryId, month}, new TransactionMapper());
    }

    @Override
    public int calculateSumOfMoneyOfAllTransactions(String username) {
        String sql = "SELECT SUM(transactions.amount) FROM transactions, clients, accounts WHERE clients.username=? AND clients.clientid = accounts.ownerid AND (accounts.iban = transactions.targetaccount OR accounts.iban = transactions.sendingaccount)";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
    }

    @Override
    public int retrieveMoneyInAccount(String username) {
        String sql="SELECT accounts.balance FROM clients, accounts WHERE clients.username=? AND clients.clientid = accounts.ownerid";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
    }

    @Override
    public void deleteById(Long transactionID) {
        jdbcTemplate.update("DELETE FROM transactions WHERE transactionid=?", transactionID);
    }

}


class TransactionMapper implements RowMapper<Transaction> {
    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();
        Category category = new Category();

        transaction.setTransactionID(rs.getLong("transactionid"));
        transaction.setTargetAccount(rs.getString("targetaccount"));
        transaction.setSendingAccount(rs.getString("sendingaccount"));
        transaction.setAmount(rs.getBigDecimal("amount"));
        transaction.setDate(rs.getTimestamp("date").toLocalDateTime());
        transaction.setCurrency(rs.getString("currency"));
        transaction.setDescription(rs.getString("description"));
        category.setId(rs.getLong("id"));
        category.setType(rs.getString("type"));
        category.setGeneralCategoryId(rs.getLong("generalcategoryid"));
        transaction.setCategory(category);
        return transaction;
    }

}
