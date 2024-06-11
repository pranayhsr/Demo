package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class DemoModel {
	@Id
    private String id;
    private String name;
    private List<DemoUserDetailsModel> transactions;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<DemoUserDetailsModel> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<DemoUserDetailsModel> transactions) {
		this.transactions = transactions;
	}
	
}
