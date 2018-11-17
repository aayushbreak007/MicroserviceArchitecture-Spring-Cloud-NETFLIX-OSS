package com.aayush.webServices.feignProxy;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.aayush.webServices.bean.CurrencyConversion;


//@FeignClient(name="currency-exchange-service")
@FeignClient(name="netflix-zuul-api-gateway-server")//to make other service call ...it will go through the api gateway
@RibbonClient(name="currency-exchange-service")

public interface CurrencyExchangeServiceProxy {

	//define a method to talk to currencyExchangeService through ZUUL api gateway in the format:
	//format: http://localhost:8765/{application name}/{uri}
	
	//@GetMapping("/currency-exchange/from/{from}/to/{to}")
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
