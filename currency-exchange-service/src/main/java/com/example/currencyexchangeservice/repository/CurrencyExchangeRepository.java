package com.example.currencyexchangeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.currencyexchangeservice.model.CurrencyExchange;

public interface CurrencyExchangeRepository 
	extends JpaRepository<CurrencyExchange, Long>{
	
	CurrencyExchange findByFromAndTo(String from,String to);
	
	List<CurrencyExchange> findAll();

}
