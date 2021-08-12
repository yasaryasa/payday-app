package com.payday;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fb")
public class HystrixController {

    @GetMapping("/stocks")
    public String stocksFallback(){
        return "Stock Service is not available.";
    }
    
    @GetMapping("/transaction")
    public String transactionFallback(){
        return "Transaction Service is not available.";
    }
}