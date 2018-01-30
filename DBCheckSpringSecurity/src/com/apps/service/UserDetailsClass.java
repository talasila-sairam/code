package com.apps.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class UserDetailsClass implements org.springframework.security.core.userdetails.UserDetails{
	
	
	public UserDetailsClass() {

	}

	private static final long serialVersionUID = 1L;
	protected String userName;
	protected String password;
	protected boolean locked;
	protected List<GrantedAuthority> granterList;
	
	public UserDetailsClass(String userName, String password,boolean locked, List<GrantedAuthority> granterList) {
		this.userName = userName;
		this.password = password;
		this.locked = locked;
		this.granterList = granterList;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return granterList;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}

