package com.example.currencyexchangeservice.controller;



import java.math.BigDecimal;
import java.util.List;


import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.currencyexchangeservice.model.CurrencyExchange;
import com.example.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@Autowired
	private Environment environment;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		String port = environment.getProperty("local.server.port");
		System.out.println("The value which we get is "+from);
		//CurrencyExchange currencyExchange = new CurrencyExchange(100L, from, to, BigDecimal.valueOf(50));
		
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		
		currencyExchange.setEnvironment(port);
		return currencyExchange;

	}
	
	@PostMapping("/currency-exchange/create")
	public ResponseEntity<CurrencyExchange> createExchange(@RequestBody CurrencyExchange currencyExchange) {
		CurrencyExchange exchange = repository.save(currencyExchange);
		if(exchange==null)
			throw new ServiceException("The value is null");
		else {
			return new ResponseEntity<>(exchange,HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/currency-exchange/getAll")
	public List<CurrencyExchange> getAll() {
		return repository.findAll();
	}
}
