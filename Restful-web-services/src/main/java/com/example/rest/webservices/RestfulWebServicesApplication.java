package com.example.rest.webservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;


@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}
	
	
	//adding a bean(modal)--localResolver for internationalization
	@Bean
	public LocaleResolver localResolver() {
		AcceptHeaderLocaleResolver lr=new AcceptHeaderLocaleResolver();
		lr.setDefaultLocale(Locale.US);
		return lr;
	}
	
	
	
}
