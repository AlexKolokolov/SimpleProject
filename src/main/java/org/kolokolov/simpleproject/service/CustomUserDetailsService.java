package org.kolokolov.simpleproject.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.dao.UserDAO;
import org.kolokolov.simpleproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDAO.getUserByName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("No user with name " + userName);
		}
		logger.debug("======== User: " + user);
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
				user.getName(), 
				user.getPassword(), 
				user.getState().getState().equals("Active"),
				true,
				true,
				true,
				getGrantedAuthorities(user));
		return userDetails;
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserProfile()));
		return authorities;
	}
	
}
