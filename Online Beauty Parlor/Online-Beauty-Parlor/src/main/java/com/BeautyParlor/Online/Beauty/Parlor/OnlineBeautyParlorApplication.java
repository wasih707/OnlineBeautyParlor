package com.BeautyParlor.Online.Beauty.Parlor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class OnlineBeautyParlorApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBeautyParlorApplication.class, args);
	}

}
