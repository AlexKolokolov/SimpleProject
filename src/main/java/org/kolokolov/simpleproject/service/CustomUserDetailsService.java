package org.kolokolov.simpleproject.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

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
		logger.debug("loadUserByUsername method runs with parameter: " + userName);
		User user = null;
		try {
			user = userDAO.getUserByName(userName);
		} catch (NoResultException nre) {
			logger.debug("Exception in userDAO: " + nre.getMessage());
			logger.debug("No user found exception has been thown");
			String exceptionMessage;
			if (userName == null || userName.equals("")) {
				exceptionMessage = "Empty user name";
			} else {
				exceptionMessage = String.format("No user with name %s has been found", userName);
			}
			throw new NoResultException(exceptionMessage);
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
