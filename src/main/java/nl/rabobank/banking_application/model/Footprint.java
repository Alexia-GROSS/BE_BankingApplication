package nl.rabobank.banking_application.model;

import java.math.BigDecimal;

public class Footprint {
    private String generalCategory;
    private BigDecimal footPrint;

    public Footprint() {
    }

    public String getGeneralCategory() {
        return generalCategory;
    }

    public void setGeneralCategory(String generalCategory) {
        this.generalCategory = generalCategory;
    }

    public BigDecimal getFootPrint() {
        return footPrint;
    }

    public void setFootPrint(BigDecimal footPrint) {
        this.footPrint = footPrint;
    }
}
