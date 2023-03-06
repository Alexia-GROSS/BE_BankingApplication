package nl.rabobank.banking_application.repository;

import nl.rabobank.banking_application.model.Category;
import nl.rabobank.banking_application.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcCategoryRepository implements CategoryRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query("SELECT * from category", BeanPropertyRowMapper.newInstance(Category.class));
    }

    @Override
    public Category getOne(Long categoryId) {
            Category categoryById = jdbcTemplate.queryForObject("SELECT * FROM category WHERE id=?",
                    new Object[]{categoryId}, Category.class);
            assert categoryById != null;
            return categoryById;
    }
}
