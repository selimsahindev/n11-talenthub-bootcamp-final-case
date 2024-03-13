package com.selimsahin.logaggregationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class LogAggregationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogAggregationServiceApplication.class, args);
	}

}
