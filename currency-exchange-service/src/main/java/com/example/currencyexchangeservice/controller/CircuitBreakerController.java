package com.example.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("sample-api")
	//@Retry(name = "sample-api",fallbackMethod = "fallBackResponse")
	//@CircuitBreaker(name = "default",fallbackMethod = "fallBackResponse")
	//@Bulkhead(name="default")
	@RateLimiter(name="default")
	public String sampleApi() {
		logger.info("Sample API Call received");
		//ResponseEntity<String> entity= new RestTemplate().getForEntity("http://localhost:6777/dummy", String.class);
		return "Sample API";
		
		//return entity.getBody();
	}
	
	
	private String fallBackResponse(Exception ex) {
		return "fallback response";
	}
}
