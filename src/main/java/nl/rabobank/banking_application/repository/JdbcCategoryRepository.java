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
public class JdbcCategoryRepository implements CategoryRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query("SELECT * from categories", BeanPropertyRowMapper.newInstance(Category.class));
    }

    @Override
    public Category getOne(Long categoryId) {
            Category categoryById = new Category();
            if(categoryId == null){
                categoryById.setId(0);
                categoryById.setType("No value");
            } else{
                categoryById = jdbcTemplate.queryForObject("SELECT * FROM categories WHERE id=?",
                        new Object[]{categoryId}, new CategoryMapper());
            }
            return categoryById;
    }

    @Override
    public Category findById(Long categoryID) {
       Category categoryById = jdbcTemplate.queryForObject("SELECT * FROM categories WHERE id=?",
               new Object[]{categoryID}, Category.class);
       assert  categoryById != null;
       return categoryById;
    }
}

class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();

        category.setId(rs.getLong("id"));
        category.setType(rs.getString("type"));

        return category;
    }
}
