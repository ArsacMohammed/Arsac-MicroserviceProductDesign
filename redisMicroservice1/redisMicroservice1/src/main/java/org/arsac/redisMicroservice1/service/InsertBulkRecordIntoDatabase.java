package org.arsac.redisMicroservice1.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.arsac.redisMicroservice1.entity.Product;
import org.arsac.redisMicroservice1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class InsertBulkRecordIntoDatabase {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ProductRepository productRepository;

	private static final String[] PRODUCT_NAMES = { "Sourdough Bread", "Baguette", "Rye Bread", "Ciabatta",
			"Multigrain Bread", "Focaccia", "Pita Bread", "Challah", "Pretzel", "Brioche" };

	@Transactional
	public void insertRecord() {
		Long startTime = System.currentTimeMillis();

		for (int i = 0; i < 40000; i++) {
			Random random = new Random();
			String productName = PRODUCT_NAMES[random.nextInt(PRODUCT_NAMES.length)];
			double randomPrice = 1.0 + (10.0 - 1.0) * random.nextDouble();
			Product product = new Product(productName, randomPrice);
			productRepository.save(product);
			if (i % 1000 == 0) {
				productRepository.flush();
				entityManager.clear();
			}
		}
		Long endTime = System.currentTimeMillis();
		System.out.println("Time taken to process the query is " + (endTime - startTime));

	}

}

/*
 * case 1 - inserting records one by one takes 18 sec .
 * case 2 - using batch with clear and flush takes 7645 (batch 50)
 * case 3 - using batch with clear and flush takes 6986 (batch 100) and for batch 500 takes 7675 and 150 - 7843
 * Decided to go with 100 batch size , Avg time taken 6.5 sec
 */




/*
 * 
 * Producing  to redis stream.
 * 	String message = String.format("Processing file: %s, appName: %s, appRunId: %s", 
	                    filePath, appName, appRunId);
				
	Map<String,String> messageForRedis = new HashMap<>();
	messageForRedis.put(filePath, message);
	messageForRedis.put("filePath", filePath);
	messageForRedis.put("message", message);
	
	// append message through RedisTemplate
	MapRecord<String, String , String > record = StreamRecords.string(messageForRedis).withStreamKey("targetPotentialSqlFiles");
	redisTemplate.opsForStream().add(record);
	
	System.out.println("Produced task for file: " + filePath);

				
}
 */

