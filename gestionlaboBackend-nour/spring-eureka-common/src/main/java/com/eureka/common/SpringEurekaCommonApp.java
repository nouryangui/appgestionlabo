package com.eureka.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class SpringEurekaCommonApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaCommonApp.class, args);
	}
}
