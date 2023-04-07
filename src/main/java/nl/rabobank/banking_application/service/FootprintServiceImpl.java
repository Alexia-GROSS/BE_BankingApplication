package nl.rabobank.banking_application.service;

import nl.rabobank.banking_application.model.Category;
import nl.rabobank.banking_application.model.Footprint;
import nl.rabobank.banking_application.model.Transaction;
import nl.rabobank.banking_application.repository.CategoryRepository;
import nl.rabobank.banking_application.repository.FootprintRepository;
import nl.rabobank.banking_application.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FootprintServiceImpl implements FootprintService{

    @Autowired
    FootprintRepository footprintRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    int footPrintValue = 0;

    public BigDecimal getFootprintPerCategory(String categoryType){
        Category category = categoryRepository.createCategoryByType(categoryType);
        BigDecimal carbonMultiplier = footprintRepository.findCarbonMultiplierForCategory(category.getId());
        List<Transaction> transactionSameCategory = transactionRepository.findTransactionsByCategory(category.getId());
        transactionSameCategory.forEach((transaction) -> {
            footPrintValue = footPrintValue + transaction.getAmount().intValue();
        });
        BigDecimal bigDecimal = BigDecimal.valueOf(footPrintValue);
        footPrintValue=0;
        return bigDecimal;
    }

    @Override
    public BigDecimal getFootPrintPerMonth(Date date) {
        return null;
    }

    /*@Override
    public List<Footprint> getFootPrintForAllCategories() {
        return null;
    }*/

    @Override
    public List<Footprint> getFootPrintForAllCategories() {
        int i =0;
        ;
        List<Footprint> listFootprint = new ArrayList<Footprint>();
        List<Category> allCategories = categoryRepository.findAll();
        Footprint[] singleFootprint = new Footprint[allCategories.size()];

        allCategories.forEach(category -> {
            singleFootprint[i]= new Footprint();
            if(getFootprintPerCategory(category.getType()).intValue() != 0){
                singleFootprint[i].setGeneralCategory(category.getType());
                singleFootprint[i].setFootPrint(getFootprintPerCategory(category.getType()));
                listFootprint.add(singleFootprint[i]);
            }
        }
        );
        return listFootprint;
    }

}
