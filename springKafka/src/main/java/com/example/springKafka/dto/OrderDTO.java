package com.example.springKafka.dto;
import java.util.List;

import lombok.Data;

@Data
public class OrderDTO {
	
	public Long id;
	public String customerName;
	public List<String> orders;
	
	
	
	public OrderDTO() {};
	
	public OrderDTO(Long Id ,String customerName , List<String>orders) {
		this.id=id;
		this.customerName=customerName;
		this.orders=orders;
	}
	
	
	

}
