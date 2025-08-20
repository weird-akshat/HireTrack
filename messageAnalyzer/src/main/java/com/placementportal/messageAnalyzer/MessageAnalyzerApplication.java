package com.placementportal.messageAnalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MessageAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageAnalyzerApplication.class, args);
	}

}
