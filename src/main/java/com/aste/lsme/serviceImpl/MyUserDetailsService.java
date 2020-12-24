package com.aste.lsme.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import com.aste.lsme.dao.UserDetailsDaoInterface;
import com.aste.lsme.domain.UserDetail;

@Transactional
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserDetailsDaoInterface userRepository;
	
	

	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetail user = userRepository.loadUserByUsername(username);

		System.out.println(user+"1234567890");
		if (user == null) 
			throw new UsernameNotFoundException("No user found with username: " + username);
		
		
		boolean enabled = user.getUserTypeFlag().equals("Active")?true:false;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(user.getEmail()));
	}

	private  List<GrantedAuthority> getAuthorities(String email) {
		
		
		 List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		  authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		  return authorities;
   }
}
