package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.DemoModel;
import com.example.demo.model.TransactionModel;

@Repository
public interface DemoRepository extends JpaRepository<DemoModel, Long> {

	
	@Query(value = "SELECT * FROM users WHERE id = :id", nativeQuery = true)
	DemoModel findUserById(@Param("id") Integer id);
	
	@Query("INSERT INTO customer (customer_points) VALUES (:points) where id = :id")
    void insertCustomer( @Param("points") Integer customerPoints,@Param("id") String id);

	 List<TransactionModel> findByCustomerIdAndTimestampBetween(Long customerId, LocalDateTime start, LocalDateTime end);
}
