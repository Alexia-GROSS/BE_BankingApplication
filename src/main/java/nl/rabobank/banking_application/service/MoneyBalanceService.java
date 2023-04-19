package nl.rabobank.banking_application.service;

import nl.rabobank.banking_application.model.MoneyBalance;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public interface MoneyBalanceService {
    public List<MoneyBalance> getMoneyBalanceForAllCategoriesForAllMonth();
    public List<MoneyBalance> getMoneyBalanceForAllCategoriesSortedPerMonth(int month) ;
    public BigDecimal getMoneyBalance(String username);
    public BigDecimal getMoneyInAccount(String username);
}
