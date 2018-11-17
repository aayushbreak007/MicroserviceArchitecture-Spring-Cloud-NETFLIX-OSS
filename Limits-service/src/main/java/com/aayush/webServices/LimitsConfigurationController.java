package com.aayush.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aayush.webServices.bean.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsConfigurationController {
	//we should pick these limits from application properties
	@Autowired 
	private Configuration configuration;
	
	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfiguration() {
		return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
	}
	
	//Adding hystrix for FAULT TOLERANCE---LIKWE TRY CATCH
	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod="fallbackRetrieveConfiguration")
	public LimitConfiguration retriveConfiguration() {
		throw new RuntimeException("Not available");
	}
	
	
	public LimitConfiguration fallbackRetrieveConfiguration() {
		return new LimitConfiguration(-1,-111);
	}

}
