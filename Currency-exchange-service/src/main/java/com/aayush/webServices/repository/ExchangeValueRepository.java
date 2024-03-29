package com.aayush.webServices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aayush.webServices.bean.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
	
	//creating a query method
	ExchangeValue findByFromAndTo(String from,String to);

}
