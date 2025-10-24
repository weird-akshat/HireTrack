package com.hiretrack.entity_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.hiretrack.entity_manager.repo")
@EnableFeignClients
public class EntityManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntityManagerApplication.class, args);
	}

}
