package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.example.demo.model.DemoModel;
import com.example.demo.model.DemoUserDetailsModel;
import com.example.demo.model.TransactionModel;

public interface DemoService {
	public DemoModel getUserById(Integer id);
	public Map<String, Integer> calculateRewardPoints(List<DemoUserDetailsModel> transactions);
	public Map<String, Integer> calculateRewardPointsPerMonth(Long customerId, LocalDateTime start, LocalDateTime end, List<TransactionModel> transactions);
}
