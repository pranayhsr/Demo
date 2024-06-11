package com.example.demo.serviceImpl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DemoModel;
import com.example.demo.model.DemoUserDetailsModel;
import com.example.demo.model.RewardModel;
import com.example.demo.model.TransactionModel;
import com.example.demo.repository.DemoRepository;
import com.example.demo.repository.RewardRepository;
import com.example.demo.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	DemoService demoService;
	
	@Autowired
	DemoRepository demoRepository;

	@Autowired
    RewardRepository rewardRepository;

	@Override
	public Map<String, Integer> calculateRewardPoints(List<DemoUserDetailsModel> transactions) {
        Map<String, Integer> rewardPoints = new HashMap<>();
        for (DemoUserDetailsModel data : transactions) {
        	DemoModel customer = data.getCustomer();
        	 int points = calculation(data.getAmount());
            rewardPoints.put(customer.getId(), points + (rewardPoints.getOrDefault(customer.getId(), 0)));
            demoRepository.insertCustomer(points, customer.getId());
        }
        return rewardPoints;
    }
	 
	@Override
	public DemoModel getUserById(Integer id) {
		return demoRepository.findUserById(id);
	}
	
	@Override
	public Map<String, Integer> calculateRewardPointsPerMonth(Long customerId, LocalDateTime start, LocalDateTime end, List<TransactionModel> transactions) {
        Map<String, Integer> rewardsPerMonth = new HashMap<>();
        for (TransactionModel transaction : transactions) {
            if (transaction.getCustomerId().equals(customerId)) {
                int points = calculation(transaction.getAmount());
                String month = transaction.getTimestamp().getMonth().toString();
                rewardsPerMonth.put(month, rewardsPerMonth.getOrDefault(month, 0) + points);
            }
        }
        saveRewardPoints(customerId, rewardsPerMonth);
        return rewardsPerMonth;
    }
	
	 public int calculation(double amount) {
	        int points = 0;
	        if (amount > 100) {
	            points += (amount - 100) * 2; // 2 points for every dollar over $100
	        }
	        if (amount > 50) {
	            points += (Math.min(amount, 100) - 50); // 1 point for every dollar between $50 and $100
	        }
	        return points;
	    }
	 
	
	 public void saveRewardPoints(Long customerId, Map<String, Integer> rewardsPerMonth) {
	        for (Map.Entry<String, Integer> entry : rewardsPerMonth.entrySet()) {
	            RewardModel reward = new RewardModel();
	            reward.setCustomerId(customerId);
	            reward.setMonth(entry.getKey());
	            reward.setPointsEarned(entry.getValue());
	            rewardRepository.save(reward);
	        }
	    }
}
