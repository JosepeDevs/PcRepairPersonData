package com.josepedevs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class UserDataManager {

	public static void main(String[] args) {
		SpringApplication.run(UserDataManager.class, args);
	}

}
