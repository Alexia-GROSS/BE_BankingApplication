package nl.rabobank.banking_application.model;


import java.math.BigDecimal;

public class GeneralCategory {
    private long generalcategoryid;
    private String name;
    private String country;
    private BigDecimal gco2;
    private String currency;
    private int confidencescore;

    public GeneralCategory(){

    }

    public GeneralCategory(String name, String country, BigDecimal gco2, String currency, int confidencescore) {
        this.name = name;
        this.country = country;
        this.gco2 = gco2;
        this.currency = currency;
        this.confidencescore = confidencescore;
    }

    public long getGeneralcategoryid() {
        return generalcategoryid;
    }

    public void setGeneralcategoryid(long generalcategoryid) {
        this.generalcategoryid = generalcategoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getGco2() {
        return gco2;
    }

    public void setGco2(BigDecimal gco2) {
        this.gco2 = gco2;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getConfidencescore() {
        return confidencescore;
    }

    public void setConfidencescore(int confidencescore) {
        this.confidencescore = confidencescore;
    }
}

