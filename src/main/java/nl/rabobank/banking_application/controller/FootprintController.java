package nl.rabobank.banking_application.controller;

import nl.rabobank.banking_application.model.Category;
import nl.rabobank.banking_application.model.Footprint;
import nl.rabobank.banking_application.service.FootprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/footprint")
public class FootprintController {

    @Autowired
    FootprintService footprintService;

    @GetMapping("/pertype/{type}")
    public ResponseEntity<BigDecimal> getFootprintPerCategory(@PathVariable("type") String categoryType ){
        BigDecimal footPrintForCategory = footprintService.getFootprintPerCategory(categoryType);
        return new ResponseEntity<>(footPrintForCategory, HttpStatus.OK);
    }

    @GetMapping("/allfootprint")
    public ResponseEntity<List<Footprint>> getAllCategoriesFootprint(){
        List<Footprint> footPrintForAllCategory = footprintService.getFootPrintForAllCategories();
        return new ResponseEntity<>(footPrintForAllCategory, HttpStatus.OK);
    }


}
