//package org.arsac.redisMicroservice1.config;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.time.Duration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.stream.Consumer;
//import org.springframework.data.redis.connection.stream.MapRecord;
//import org.springframework.data.redis.connection.stream.ReadOffset;
//import org.springframework.data.redis.connection.stream.StreamOffset;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.stream.StreamListener;
//import org.springframework.data.redis.stream.StreamMessageListenerContainer;
//import org.springframework.data.redis.stream.Subscription;
//
//
//@Configuration
//public class MessageListenerContainer {
//	
//	@Value("${stream.key:targetPotentialSqlFiles}")
//	private String streamKey;
//	
//	@Autowired
//	private RedisConsumer redisConsumer;
//	
//	@Autowired 
//	private RedisTemplate<String, ?> redisTemplate;
//	
//	private int count=0;
//	
//
//	@Bean
//	public Subscription subscription(RedisConnectionFactory connectionFactory) throws UnknownHostException {
//
//	        redisConsumer.createConsumerGroupIfNotExists(connectionFactory, streamKey, streamKey);
//
//	        StreamOffset<String> streamOffset = StreamOffset.create(streamKey, ReadOffset.lastConsumed());
//
//	        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String,
//	                MapRecord<String, String,String>> options = StreamMessageListenerContainer
//	                .StreamMessageListenerContainerOptions
//	                .builder()
//	                .pollTimeout(Duration.ofMillis(1000))
//	                .build();
//
//	        StreamMessageListenerContainer<String, MapRecord<String,String,String>>  container =
//	                StreamMessageListenerContainer.create(connectionFactory, options);
//
//	        Subscription subscription =
//	                container.receiveAutoAck(Consumer.from(streamKey, InetAddress.getLocalHost().getHostName()),
//	                streamOffset, streamListener());
//
//	        container.start();
//	        return subscription;
//	}
//
//	@Bean
//	public StreamListener<String, MapRecord<String,String ,String>> streamListener() {
//	  // handle message from stream
//		
//	  return new StreamListener<String, MapRecord<String,String,String>>() {
//		  
//		
//		@Override
//		public void onMessage(MapRecord<String, String, String> message) {
//			System.out.println("MessageId: " + message.getId());
//			System.out.println("Stream: " + message.getStream());
//			System.out.println("Body: " + message.getValue());
//			
//			System.out.println(count+=1);
//			
//			redisTemplate.opsForStream().delete(message.getStream(),message.getId());
//		}
//	} ;
//	}
//
//
//}
