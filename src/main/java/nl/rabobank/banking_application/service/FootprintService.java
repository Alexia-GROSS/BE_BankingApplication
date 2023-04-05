package nl.rabobank.banking_application.service;

import nl.rabobank.banking_application.model.Category;
import nl.rabobank.banking_application.model.Footprint;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public interface FootprintService {

    public BigDecimal getFootprintPerCategory(String categoryType);
    public BigDecimal getFootPrintPerMonth(Date date);

    public List<Footprint> getFootPrintForAllCategories();
}
