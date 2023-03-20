package nl.rabobank.banking_application.service;

import nl.rabobank.banking_application.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryService {
    List<Category> getAllCategory();
    Category getASingleCategory(Long categoryID);
}
