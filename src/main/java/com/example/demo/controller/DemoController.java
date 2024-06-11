package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DemoModel;
import com.example.demo.model.DemoUserDetailsModel;
import com.example.demo.model.TransactionModel;
import com.example.demo.repository.DemoRepository;
import com.example.demo.service.DemoService;

@CrossOrigin
@RestController
public class DemoController {
	
 @Autowired
 DemoService demoservice;

 
 //calculating points for customers
 @PostMapping("/calculate")
 public Map<String, Integer> calculateRewardPoints(@RequestBody List<DemoUserDetailsModel> transactions) {
     return demoservice.calculateRewardPoints(transactions);
 }
 
 //getting reward points based on id
 @GetMapping("/{id}")
 public ResponseEntity<DemoModel> getUserById(@PathVariable Integer id) {
	 DemoModel user = demoservice.getUserById(id);
    
         return ResponseEntity.ok(user);
 }
 
 //reward Points per month
 @GetMapping("/reward-points/{customerId}")
 public ResponseEntity<Map<String, Integer>> getRewardPointsForCustomer(
         @PathVariable Long customerId,
         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
     
     List<TransactionModel> transactions;
     if (start != null && end != null) {
         transactions = transactionRepository.findByCustomerIdAndTimestampBetween(customerId, start, end);
     } else {
         transactions = transactionRepository.findAll();
     }
     
     Map<String, Integer> rewardsPerMonth = demoservice.calculateRewardPointsPerMonth(customerId, start, end, transactions);
     return ResponseEntity.ok(rewardsPerMonth);
 }
 
 

}
