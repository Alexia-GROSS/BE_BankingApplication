package nl.rabobank.banking_application.model;

import java.math.BigDecimal;

public class MoneyBalance {

    private String generalCategory;
    private BigDecimal moneyBalance;

    public MoneyBalance() {
    }

    public String getGeneralCategory() {
        return generalCategory;
    }

    public void setGeneralCategory(String generalCategory) {
        this.generalCategory = generalCategory;
    }

    public BigDecimal getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(BigDecimal moneyBalance) {
        this.moneyBalance = moneyBalance;
    }
}
