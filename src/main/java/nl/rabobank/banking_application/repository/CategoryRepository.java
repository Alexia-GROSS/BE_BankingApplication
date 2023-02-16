package nl.rabobank.banking_application.repository;

import nl.rabobank.banking_application.model.Category;
import nl.rabobank.banking_application.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
