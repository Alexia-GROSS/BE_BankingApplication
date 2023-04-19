package nl.rabobank.banking_application.service;

import nl.rabobank.banking_application.model.Category;
import nl.rabobank.banking_application.model.Footprint;
import nl.rabobank.banking_application.model.MoneyBalance;
import nl.rabobank.banking_application.model.Transaction;
import nl.rabobank.banking_application.repository.CategoryRepository;
import nl.rabobank.banking_application.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MoneyBalanceImpl implements MoneyBalanceService{

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    int sumMoneyTransactions=0;

    private BigDecimal getMoneyBalancePerCategory(String categoryType) {
        Category category = categoryRepository.createCategoryByType(categoryType);
        List<Transaction> transactionSameCategory = transactionRepository.findTransactionsByCategory(category.getId());
        transactionSameCategory.forEach((transaction) -> {
            sumMoneyTransactions = sumMoneyTransactions + transaction.getAmount().intValue();
        });
        BigDecimal bigDecimal = BigDecimal.valueOf(sumMoneyTransactions);
        sumMoneyTransactions=0;
        return bigDecimal;
    }

    private BigDecimal getMoneyBalancePerCategoryPerMonth(String categoryType, int month) {
        Category category = categoryRepository.createCategoryByType(categoryType);
        List<Transaction> transactionSameCategory = transactionRepository.findTransactionsByCategoryAndMonth(category.getId(), month);
        transactionSameCategory.forEach((transaction) -> {
            sumMoneyTransactions = sumMoneyTransactions + transaction.getAmount().intValue();
        });
        BigDecimal bigDecimal = BigDecimal.valueOf(sumMoneyTransactions);
        sumMoneyTransactions=0;
        return bigDecimal;
    }


    @Override
    public List<MoneyBalance> getMoneyBalanceForAllCategoriesForAllMonth() {
        int i =0;
        List<MoneyBalance> moneyBalance = new ArrayList<MoneyBalance>();
        List<Category> allCategories = categoryRepository.findAll();
        MoneyBalance[] singleMoneyBalancePerCategory = new MoneyBalance[allCategories.size()];

        allCategories.forEach(category -> {
            singleMoneyBalancePerCategory[i]= new MoneyBalance();
                    if(getMoneyBalancePerCategory(category.getType()).intValue() != 0){
                        singleMoneyBalancePerCategory[i].setGeneralCategory(category.getType());
                        singleMoneyBalancePerCategory[i].setMoneyBalance(getMoneyBalancePerCategory(category.getType()));
                        moneyBalance.add(singleMoneyBalancePerCategory[i]);
                    }
                }
        );
        return moneyBalance;
    }

    @Override
    public List<MoneyBalance> getMoneyBalanceForAllCategoriesSortedPerMonth(int month) {
        int i =0;
        List<MoneyBalance> moneyBalance = new ArrayList<MoneyBalance>();
        List<Category> allCategories = categoryRepository.findAll();
        MoneyBalance[] singleMoneyBalancePerCategory = new MoneyBalance[allCategories.size()];

        allCategories.forEach(category -> {
            singleMoneyBalancePerCategory[i]= new MoneyBalance();
                    if(getMoneyBalancePerCategoryPerMonth(category.getType(), month).intValue() != 0){
                        singleMoneyBalancePerCategory[i].setGeneralCategory(category.getType());
                        singleMoneyBalancePerCategory[i].setMoneyBalance(getMoneyBalancePerCategoryPerMonth(category.getType(), month));
                        moneyBalance.add(singleMoneyBalancePerCategory[i]);
                    }
                }
        );
        return moneyBalance;
    }

    @Override
    public BigDecimal getMoneyBalance(String username) {
        int finalMoneyBalance = transactionRepository.calculateSumOfMoneyOfAllTransactions(username);
        return BigDecimal.valueOf(finalMoneyBalance);
    }

    @Override
    public BigDecimal getMoneyInAccount(String username) {
        int moneyInAccount = transactionRepository.retrieveMoneyInAccount(username) - transactionRepository.calculateSumOfMoneyOfAllTransactions(username);
        return BigDecimal.valueOf(moneyInAccount);
    }
}
