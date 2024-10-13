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
}
