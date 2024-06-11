package com.example.demo.model;

import java.util.Date;

@Data
@Entity
public class DemoUserDetailsModel {
	
	 	@Id
	    private Long id;
	    private Date date;
	    private Double amount;
	    private DemoModel customer;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		public DemoModel getCustomer() {
			return customer;
		}
		public void setCustomer(DemoModel customer) {
			this.customer = customer;
		}
  
	    
}
