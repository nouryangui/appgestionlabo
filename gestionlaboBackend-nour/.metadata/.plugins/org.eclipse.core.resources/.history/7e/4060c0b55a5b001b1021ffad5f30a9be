package com.eureka.auth.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.auth.entities.AppUser;
import com.eureka.auth.payload.response.MessageResponse;
import com.eureka.auth.services.AccountService;;

@RestController
public class AccountRestController {
	@Autowired
	private AccountService accountService;

	@PostMapping(value = "/register")
	public ResponseEntity<?> register(@RequestBody RegisterForm userForm) {

		AppUser userByName = accountService.findUserByUserName(userForm.getUsername());
		if (userByName != null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		AppUser userByEmail = accountService.findUserByEmail(userForm.getEmail());

		if (userByEmail != null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		AppUser appUser = new AppUser();

		appUser.setUsername(userForm.getUsername());
		appUser.setPassword(userForm.getPassword());
		appUser.setEmail(userForm.getEmail());
		accountService.saveUser(appUser);
		accountService.addRoleToUser(userForm.getUsername(), "user");
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
