package nl.rabobank.banking_application.repository;

import nl.rabobank.banking_application.model.Category;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface FootprintRepository {

    BigDecimal findCarbonMultiplierForCategory(Long categoryId);
}
