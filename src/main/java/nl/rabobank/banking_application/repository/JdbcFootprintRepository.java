package nl.rabobank.banking_application.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class JdbcFootprintRepository implements FootprintRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public BigDecimal findCarbonMultiplierForCategory(Long categoryId) {
        String sql = "SELECT general_categories.gco2 from general_categories, categories WHERE categories.generalcategoryid = general_categories.generalcategoryid AND categories.id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{categoryId}, BigDecimal.class);
    }
}
