package org.arsac.redisMicroservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAsync
public class RedisMicroservice1Application {

	public static void main(String[] args) {
		SpringApplication.run(RedisMicroservice1Application.class, args);
	}
	
	
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
