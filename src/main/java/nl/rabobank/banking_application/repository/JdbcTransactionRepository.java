package nl.rabobank.banking_application.repository;

import nl.rabobank.banking_application.model.Category;
import nl.rabobank.banking_application.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
        Transaction transactionById = jdbcTemplate.queryForObject("SELECT * FROM transactions WHERE transactionid=?",
                new Object[]{transactionID}, Transaction.class);
        assert transactionById != null;
        return Optional.of(
                transactionById
            );
    }

    @Override
    public List<Transaction> findAll() {
        return jdbcTemplate.query("SELECT * from transactions", new TransactionMapper());
    }

    @Override
    public void deleteById(Long transactionID) {
        jdbcTemplate.update("DELETE FROM transactions WHERE transactionid=?", transactionID);
    }

}


class TransactionMapper implements RowMapper<Transaction> {
    @Autowired
    JdbcCategoryRepository categoryRepository;

    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();

        transaction.setTransactionID(rs.getLong("transactionid"));
        transaction.setTargetAccount(rs.getString("targetaccount"));
        transaction.setSendingAccount(rs.getString("sendingaccount"));
        transaction.setAmount(rs.getBigDecimal("amount"));
        transaction.setDate(rs.getTimestamp("date").toLocalDateTime());
        transaction.setCurrency(rs.getString("currency"));
        /*transaction.getCategory().setId(rs.getLong("category"));*/
        Category newCategory = categoryRepository.getOne(rs.getLong("category"));
        transaction.setCategory(newCategory);
        return transaction;
    }
}
