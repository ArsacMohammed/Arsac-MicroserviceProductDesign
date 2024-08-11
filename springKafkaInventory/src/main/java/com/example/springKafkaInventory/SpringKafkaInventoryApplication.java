package com.example.springKafkaInventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class SpringKafkaInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaInventoryApplication.class, args);
	}

}
