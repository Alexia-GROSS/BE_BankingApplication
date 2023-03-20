package nl.rabobank.banking_application.repository;

import nl.rabobank.banking_application.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository {
    List<Category> findAll();

    Category getOne(Long categoryId);

    Category findById(Long categoryID);
}
