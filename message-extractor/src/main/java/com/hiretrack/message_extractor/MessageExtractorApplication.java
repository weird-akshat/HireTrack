package com.hiretrack.message_extractor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class MessageExtractorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageExtractorApplication.class, args);
	}

}
