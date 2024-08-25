package com.example.springKafka.controller;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.netflix.discovery.converters.Auto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.web.bind.annotation.*;

import com.example.springKafka.dto.OrderDTO;
import com.example.springKafka.entity.Order;
import com.example.springKafka.service.OrderService;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/order/")
public class OrderController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;


	@Value("${springKafka.service.url}")
	public String springKafkaServiceUrl;

	@Value("${inventoryUrl}")
	public String inventoryUrl;


	public final RestTemplate restTemplate;

	@Autowired
	public OrderController(RestTemplate restTemplate){
		this.restTemplate= restTemplate;
	}


    @GetMapping("/config")
	public String getConfig() {
		return "Order Service URL: " + springKafkaServiceUrl;
	}

	@GetMapping("sendKafkaMsg")
	public String sendKafkaMessage(){
		if (orderService.checkKafkaMessageTransferAfterSecurity()){
			return "got that message";
		}
		return "kafka not send some security issues";
	}
	
	@PostMapping("/createOrder")
	public String createOrder(@RequestBody OrderDTO orderdto) {
		OrderDTO savedOrder= orderService.createOrder(orderdto);
		System.out.println("Received new order: " + savedOrder);

		return "Order received successfully";

	}




	@GetMapping("/getAllOrder")
    public String getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            LOGGER.info("No orders found.");
        } else {
            for (OrderDTO order : orders) {
                LOGGER.info(order.toString());
            }
        }
        return "All orders returned successfully";
    }

	@GetMapping("/variablePrint")
	public String printVariable(){
		System.out.println("variable :: "+inventoryUrl);
		return "printed successfully  " + inventoryUrl;
	}

    public String fallBackMethod(Exception e) {
		System.out.println("Fallback method called due to: " + e.getMessage());
		return "fallback method is called";
    }
	
	@GetMapping(value = "/ping")
	public String getTestData() {
		return "SUCCESS";
	}
	
	
	@GetMapping("/getOrderById/{Id}")
	public  OrderDTO getOrderById(@PathVariable Long Id) {
		return orderService.getOrderById(Id);
	}

	@CircuitBreaker(name="inventoryServiceCircuitBreaker",fallbackMethod="fallBackMethod")
	@GetMapping("/getAllInventoryFromInventoryService")
	public String getAllInventoryFromInventoryService() {
		try {
			// Construct the URL for the Inventory Service
			String inventoryURL = inventoryUrl + "/getAllInventories";
			System.out.println("Fetching inventory from: " + inventoryURL);

			// Make the HTTP GET request to the Inventory Service
			ResponseEntity<JsonNode> response = restTemplate.exchange(
					inventoryURL,
					HttpMethod.GET,
					null,
					JsonNode.class
			);

			// Extract the body of the response
			JsonNode inventoryList = response.getBody();

			if (inventoryList != null && inventoryList.isArray() && inventoryList.size() > 0) {
				// Print each inventory item to the console
				inventoryList.forEach(item -> {
					// Print each field of the JSON object
					System.out.println("Product ID: " + item.get("productId").asText());
					System.out.println("Product Name: " + item.get("productName").asText());
					System.out.println("Quantity: " + item.get("quantity").asInt());
					System.out.println(); // Print an empty line for readability
				});
			} else {
				System.out.println("Inventory is empty.");
			}
		} catch (Exception e) {
			// Print exception to console
			throw e;
//			e.printStackTrace();
//			return "Something went wrong with fetching the inventory list: " + e.getMessage();
		}
		return "Inventory list fetched and printed successfully.";
	}
}




