package com.hiretrack.message_analyzer.message_analyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MessageAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageAnalyzerApplication.class, args);
	}

}
