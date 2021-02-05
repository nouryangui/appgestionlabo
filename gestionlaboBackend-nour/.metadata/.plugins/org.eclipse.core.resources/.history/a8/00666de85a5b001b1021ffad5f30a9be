package com.eureka.auth.services;

import com.eureka.auth.entities.AppRole;
import com.eureka.auth.entities.AppUser;

public interface AccountService {
	public AppUser saveUser(AppUser user);

	public AppRole saveRole(AppRole appRole);

	public AppUser findUserByUserName(String username);
	public AppUser findUserByEmail(String email);

	public void addRoleToUser(String username, String role);
}
