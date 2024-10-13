package org.arsac.redisMicroservice1.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.arsac.redisMicroservice1.service.ConcurrentHashMapRedisAlternative;
import org.arsac.redisMicroservice1.service.InsertBulkRecordIntoDatabase;
import org.arsac.redisMicroservice1.service.MultipleProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class controller {
	@Autowired
	private ConcurrentHashMapRedisAlternative concurrentHashMapRedisAlternative;

	@Autowired
	private InsertBulkRecordIntoDatabase insertBulkRecordIntoDatabase;
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MultipleProcessing taskService;

//	@GetMapping("/api/call-hello")
//	public String callHelloWorldApi() {
//		String secondPCUrl = "http://192.168.137.1:8080/api/hello"; // Replace with PC 2's actual IP
//
//		try {
//			// Sending GET request to PC 2
//			String response = restTemplate.getForObject(secondPCUrl, String.class);
//			return "Response from PC 2: " + response;
//		} catch (Exception e) {
//			return "Error occurred: " + e.getMessage();
//		}
//	}


	@GetMapping("/api/insertRecord")
	public String insertRecord() {
		insertBulkRecordIntoDatabase.insertRecord();
		return "sucessfully inserted the records";
	}

	@GetMapping("/putValueInCache/{mapName}")
	public String putValueInCache(@PathVariable String mapName) {
		return concurrentHashMapRedisAlternative.putValue(mapName);

	}

	@GetMapping("/{mapName}/{key}")
	public Object getValue(@PathVariable String mapName, @PathVariable String key) {
		return concurrentHashMapRedisAlternative.getValue(mapName, key);
	}

	@DeleteMapping("/{mapName}/{key}")
	public void removeValue(@PathVariable String mapName, @PathVariable String key) {
		concurrentHashMapRedisAlternative.removeValue(mapName, key);
	}

	@GetMapping("/{mapName}")
	public String getAllEntries(@PathVariable String mapName) {
		return concurrentHashMapRedisAlternative.getAllEntries(mapName);
	}

	@PostMapping("/{mapName}/expire")
	public void setExpiry(@PathVariable String mapName, @RequestParam long timeout) {
		concurrentHashMapRedisAlternative.setExpiry(mapName, timeout, TimeUnit.MINUTES);
	}

	
	
	
	/*-----------------------------completable future implementation ------------------------------------*/
	
	
	@GetMapping("/runTasks")
	public String runTasks() throws Exception {
		Long startTime=System.currentTimeMillis();
		CompletableFuture<String> task1 = taskService.processTask1();
		CompletableFuture<String> task2 = taskService.processTask2();

		// Wait for both tasks to complete
		CompletableFuture.allOf(task1, task2).join();
		Long endTime=System.currentTimeMillis();
		Long timeTaken= endTime-startTime;
		System.out.println("Time taken by async is :: "+timeTaken);
		return "Both Tasks Completed: " + task1.get() + ", " + task2.get();
	}
	
	
	@GetMapping("/processDataAsync")
	public String processDataAsync() throws InterruptedException, ExecutionException, TimeoutException {
		Long startTime=System.currentTimeMillis();		
		CompletableFuture<String> process1 = taskService.fetchUserData();
		CompletableFuture<String> process2  =taskService.fetchOrderData();
		
		
		CompletableFuture<String> combinedFuture = process1
				.thenCombine(process2, (userData,orderData) -> {
					try {
						return taskService.processingData(userData, orderData).get();
					} catch (InterruptedException | ExecutionException e) {
	                    throw new RuntimeException(e);
					}
				}).exceptionally(ex -> "Error: " + ex.getMessage());  // Handling errors;
		Long endTime=System.currentTimeMillis();
		Long timeTaken= endTime-startTime;
		String result = combinedFuture.get(10, TimeUnit.SECONDS);
		System.out.println("Time taken by async is :: "+timeTaken);
		return "processing done successfully";
	}
}
