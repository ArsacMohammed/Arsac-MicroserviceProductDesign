package org.arsac.redisMicroservice1.controller;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.arsac.redisMicroservice1.service.ConcurrentHashMapRedisAlternative;
import org.arsac.redisMicroservice1.service.InsertBulkRecordIntoDatabase;
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
	public String  putValueInCache(@PathVariable String mapName) {
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
    public String  getAllEntries(@PathVariable String mapName) {
        return concurrentHashMapRedisAlternative.getAllEntries(mapName);
    }

    @PostMapping("/{mapName}/expire")
    public void setExpiry(@PathVariable String mapName, @RequestParam long timeout) {
        concurrentHashMapRedisAlternative.setExpiry(mapName, timeout, TimeUnit.MINUTES);
    }

}
