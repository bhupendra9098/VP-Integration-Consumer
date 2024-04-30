package com.moglix.integrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import com.rabbitmq.client.ConnectionFactory;

@ComponentScan({"com.moglix"})
@SpringBootApplication
@EnableMongoRepositories({"com.moglix"})
@EnableSpringDataWebSupport
public class IntegratorApplication {
	
	@Bean
	public ConnectionFactory connectionFactory(){
		return new ConnectionFactory();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(IntegratorApplication.class, args);
	}
	
}
