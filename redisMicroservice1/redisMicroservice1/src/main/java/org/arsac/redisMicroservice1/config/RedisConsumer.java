package org.arsac.redisMicroservice1.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.stereotype.Service;


@Service
public class RedisConsumer {
	@Value("${stream.key:targetPotentialSqlFiles}")
	private String streamKey;
	
	@Value("${group.name:fileProcessingGroup")
	private String groupName;

	
	private final static Logger LOGGER = LoggerFactory.getLogger(RedisConsumer.class);
	public  void createConsumerGroupIfNotExists(RedisConnectionFactory redisConnectionFactory, 
	    String streamKey, String groupName){
	       
	         try {
	            try {
	                redisConnectionFactory.getConnection().streamCommands()
	                        .xGroupCreate(streamKey.getBytes(), groupName, ReadOffset.from("0-0"), true);
	            } catch (RedisSystemException exception) {
	            	LOGGER.warn(exception.getCause().getMessage());
	            }
	        }
	        catch (RedisSystemException ex){
	        	LOGGER.error(ex.getMessage());
	        }
	}
	
}

