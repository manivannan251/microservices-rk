package com.example.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.limitsservice.bean.Limits;
import com.example.limitsservice.configuration.LimitsConfiguration;

@RestController
public class LimitsServiceController {
	
	@Autowired
	private LimitsConfiguration config;
	
	@GetMapping("/limits")
	public Limits retrieveLimits() {
		//return new Limits(1, 1000);
		return new Limits(config.getMinimum(), config.getMaximum());
	}
}
