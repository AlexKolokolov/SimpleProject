package org.kolokolov.simpleproject.controller;

import javax.faces.bean.ManagedBean;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@ManagedBean
public class SecurityController {

	String userName;
	
	{
		userName = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
