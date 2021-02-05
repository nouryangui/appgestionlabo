package com.eureka.auth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

@EnableDiscoveryClient
public class SpringEurekaAuthApp implements CommandLineRunner {
	
//@Autowired
//MemberProxy memberProxy;
	public static void main(String[] args)   {
		SpringApplication.run(SpringEurekaAuthApp.class, args);
	
	}
	@Override
	public void run(String... args) throws Exception {
//		MemberBean member= memberProxy.findByUsername("nour.yangui");
//		System.out.print(member.getEmail());
		
	}
	
}
