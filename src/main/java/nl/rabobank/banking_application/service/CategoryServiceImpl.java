package nl.rabobank.banking_application.service;


import nl.rabobank.banking_application.exception.ResourceNotFoundException;
import nl.rabobank.banking_application.model.Category;
import nl.rabobank.banking_application.model.Transaction;
import nl.rabobank.banking_application.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Category getASingleCategory(Long categoryID) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(categoryID);
        return  category;
    }
}
