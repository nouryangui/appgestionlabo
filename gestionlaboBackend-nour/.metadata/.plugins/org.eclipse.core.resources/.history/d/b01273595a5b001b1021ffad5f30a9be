package com.eureka.auth.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eureka.auth.dao.RoleRepository;
import com.eureka.auth.dao.UserRepository;
import com.eureka.auth.entities.AppRole;
import com.eureka.auth.entities.AppUser;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	// crypter mdp avant le stocker
	@Override
	public AppUser saveUser(AppUser user) {
		String hashPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPassword);
		userRepository.save(user);
		return user;
	}

	@Override
	public AppRole saveRole(AppRole appRole) {
		return roleRepository.save(appRole);
	}

	public void addRoleToUser(String username, String role) {
		AppRole appRole = roleRepository.findByRoleName(role);
		AppUser appUser = userRepository.findByUsername(username);
		List<AppRole> roles = appUser.getRoles();
		roles.add(appRole);
		userRepository.save(appUser);
	}

	@Override
	public AppUser findUserByUserName(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public AppUser findUserByEmail(String email) {
		return userRepository.findByEmail(email);
		
	}

}
