package tn.enis.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tn.enis.member.bean.EventBean;
import tn.enis.member.bean.ToolBean;
import tn.enis.member.proxy.EventProxy;
import tn.enis.member.proxy.ToolProxy;
import tn.enis.member.service.MemberServiceImpl;
;
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MemberApplication implements CommandLineRunner {
	@Autowired
	ToolProxy toolProxy;
	@Autowired
	EventProxy eventProxy; 
	@Autowired
	MemberServiceImpl memberServiceImpl;
	public static void main(String[] args) {
		SpringApplication.run(MemberApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	} 
	@Bean
	BCryptPasswordEncoder getBCPE()
	{
		return new BCryptPasswordEncoder();
	}

}