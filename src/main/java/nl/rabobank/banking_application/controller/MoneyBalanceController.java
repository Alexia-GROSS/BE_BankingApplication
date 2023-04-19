package nl.rabobank.banking_application.controller;

import nl.rabobank.banking_application.model.Footprint;
import nl.rabobank.banking_application.model.MoneyBalance;
import nl.rabobank.banking_application.service.MoneyBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/moneybalance")
public class MoneyBalanceController {

    @Autowired
    MoneyBalanceService moneyBalanceService;

    @GetMapping("/moneybalancepercategory")
    public ResponseEntity<List<MoneyBalance>> getAllCategoriesMoneyBalance(){
        List<MoneyBalance> moneyBalanceForAllCategory = moneyBalanceService.getMoneyBalanceForAllCategoriesForAllMonth();
        return new ResponseEntity<>(moneyBalanceForAllCategory, HttpStatus.OK);
    }

    @GetMapping("/moneybalancepercategorypermoney/perdate/{date}")
    public ResponseEntity<List<MoneyBalance>> getMoneySpentForAllCategoriesSortedPerMonth(@PathVariable("date") int month ){
        List<MoneyBalance> moneyBalanceForAllCategoryPerMonth = moneyBalanceService.getMoneyBalanceForAllCategoriesSortedPerMonth(month);
        return new ResponseEntity<>(moneyBalanceForAllCategoryPerMonth, HttpStatus.OK);
    }

    @GetMapping("/sumoftransactions")
    public ResponseEntity<BigDecimal> getMoneyBalance(Principal principal){
        String username = principal.getName();
        BigDecimal moneyBalance = moneyBalanceService.getMoneyBalance(username);
        return new ResponseEntity<>(moneyBalance, HttpStatus.OK);
    }

    @GetMapping("/moneyinaccount")
    public ResponseEntity<BigDecimal> getMoneyInAccount(Principal principal){
        String username = principal.getName();
        BigDecimal moneyInAccount = moneyBalanceService.getMoneyInAccount(username);
        return new ResponseEntity<>(moneyInAccount, HttpStatus.OK);
    }

}
