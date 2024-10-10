package org.arsac.redisMicroservice1.service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class ConcurrentHashMapRedisAlternative {
	
	
	@Autowired
	public RedisTemplate<String, String> redisTemplate;
	
	public String putValue(String mapName) {
		
		String[] keys = {"one","two","three","four"};
		String[] values= {"1","2","3","4"};
		int i=0;
		for(String key:keys) {
			redisTemplate.opsForHash().put(mapName, key, values[i]);
			i++;
		};
		
		return "cache inserted";
	}
	
	  // Simulate get operation (similar to ConcurrentHashMap)
    public Object getValue(String mapName, String key) {
        return redisTemplate.opsForHash().get(mapName, key);
    }

    // Simulate delete operation (similar to ConcurrentHashMap)
    public void removeValue(String mapName, String key) {
        redisTemplate.opsForHash().delete(mapName, key);
    }

    // Simulate entire map fetch
    public String getAllEntries(String mapName) {
        Map<Object, Object>numbers= redisTemplate.opsForHash().entries(mapName);
        for (Map.Entry<Object, Object> entry:numbers.entrySet()) {
        	System.out.println("Key :: "+entry.getKey()+" "+"Value"+entry.getValue());
        }
        return "Successfully fetched";
    }

    // Set an expiration time for the entire map
    public void setExpiry(String mapName, long timeout, TimeUnit unit) {
        redisTemplate.expire(mapName, timeout, unit);
    }
}
