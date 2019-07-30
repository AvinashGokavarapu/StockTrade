package com.example.tradedemo;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication(exclude = { ErrorMvcAutoConfiguration.class })
public class TradedemoApplication {
	
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	public static void main(String[] args) {
		SpringApplication.run(TradedemoApplication.class, args);
		
	}
		
		
		@Bean
		public SessionFactory getSessionFactory() {
			SessionFactory sessFactory =  entityManagerFactory.unwrap(SessionFactory.class);;
			return sessFactory;
		}
		
		
		@Bean
		public RestTemplate restTemplate(RestTemplateBuilder builder) {
		   return builder.build();
		}
	

}
