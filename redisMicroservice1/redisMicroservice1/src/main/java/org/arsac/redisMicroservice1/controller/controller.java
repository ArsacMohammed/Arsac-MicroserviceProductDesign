package org.arsac.redisMicroservice1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class controller {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/api/call-hello")
	public String callHelloWorldApi() {
		String secondPCUrl = "http://192.168.137.1:8080/api/hello"; // Replace with PC 2's actual IP

		try {
			// Sending GET request to PC 2
			String response = restTemplate.getForObject(secondPCUrl, String.class);
			return "Response from PC 2: " + response;
		} catch (Exception e) {
			return "Error occurred: " + e.getMessage();
		}
	}

}
