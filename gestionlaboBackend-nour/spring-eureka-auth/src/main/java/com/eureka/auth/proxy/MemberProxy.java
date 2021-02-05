package com.eureka.auth.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eureka.auth.beans.MemberBean;
import com.eureka.auth.beans.RoleBean;

@FeignClient("member-service")

public interface MemberProxy {
	@GetMapping("/api/members/role/{rolename}")
	RoleBean findByRolename(@PathVariable String rolename);
	@GetMapping("/api/members/username/{username}")
	MemberBean findByUsername(@PathVariable String username);
}
