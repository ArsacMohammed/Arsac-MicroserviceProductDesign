package org.arsac.redisMicroservice1.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class MultipleProcessing {
	@Async
	public CompletableFuture<String> processTask1() throws InterruptedException {
		Thread.sleep(2000); // Simulating a long-running task
		return CompletableFuture.completedFuture("Task 1 Completed");
	}

	@Async
	public CompletableFuture<String> processTask2() throws InterruptedException {
		Thread.sleep(3000); // Simulating a longer-running task
		return CompletableFuture.completedFuture("Task 2 Completed");
	}
	
	
	
	@Async
	public CompletableFuture<String> fetchUserData() throws InterruptedException{
		Thread.sleep(3000);
		return CompletableFuture.completedFuture("user data  fetched.");
	}
	
	
	@Async
	public CompletableFuture<String> fetchOrderData() throws InterruptedException{
		Thread.sleep(2000);
		return CompletableFuture.completedFuture("user data  fetched.");
	}
	
	@Async
	public CompletableFuture<String> processingData(String userData,String orderData) throws InterruptedException{
		Thread.sleep(4000);
		return CompletableFuture.completedFuture("processing  data  fetched :: "+userData + " :: "+orderData);
	}
	
}
