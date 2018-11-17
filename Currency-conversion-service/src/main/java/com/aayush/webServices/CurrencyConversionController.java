package com.aayush.webServices;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.aayush.webServices.bean.CurrencyConversion;
import com.aayush.webServices.feignProxy.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	//getting the Feign proxy
	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	

	//Using: FEIGN (spring-cloud component)
			// it solves the problem 1: writing lots of code for a simple service call
			// it provides the integration with the " Client side load balancer called "Ribbon"
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion convertCurrencyFeign(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
	
		CurrencyConversion response=proxy.retrieveExchangeValue(from, to);	
		logger.info("{}",response);
		return new CurrencyConversion(response.getId(),from,to,response.getConversionMultiple(),
				quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());	
		
	}
	
	//using rest template
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion convertCurrency(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		//here we need to call "Currency Exchange Service" to get the conversion multiple
		//we can invoke other services by using "REST TEMPLATE"
		
		Map<String,String> uriVariables=new HashMap<String,String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity=
				new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}"
				, CurrencyConversion.class
				,uriVariables);
		CurrencyConversion response=responseEntity.getBody();
		return new CurrencyConversion(response.getId(),from,to,response.getConversionMultiple(),
				quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());
		
		
		
		
	}

}
