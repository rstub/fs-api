package me.stubner.fsapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fatsecret.platform.services.FatsecretService;

@SpringBootApplication
public class FsApiApplication {

	@Bean
	protected FatsecretService initFatsecretService(
			@Value("${FATSECRET_CONSUMER_KEY}") String key,
			@Value("${FATSECRET_CONSUMER_SECRET}") String secret
			) {
		return new FatsecretService(key, secret);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(FsApiApplication.class, args);
	}

}
