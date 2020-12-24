package com.aste.lsme.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.service.UserDetailsServiceInterface;


@Service
@Component
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserDetailsServiceInterface userDetailsService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetail appUser = userDetailsService.loadUserByUsername(username);
		
		if (appUser != null) {
			Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
			return new User(appUser.getUsername(),appUser.getPassword(),grantedAuthorities);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}