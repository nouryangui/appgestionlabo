package com.eureka.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eureka.auth.beans.MemberBean;
import com.eureka.auth.proxy.MemberProxy;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	MemberProxy memberProxy;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		MemberBean user = memberProxy.findByUsername(username);
		if(user == null) throw new UsernameNotFoundException("invalid user");
				//.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);		
	}

}