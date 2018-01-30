package com.apps.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apps.BO.UserBO;
import com.apps.dao.UserDao;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	private UserDao userDao;
	
	public UserService() {
		
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserBO user=userDao.valiadateUser(userName);
		System.out.println(user);
		boolean status = false;
		List<GrantedAuthority> granterList=new ArrayList<>();
		GrantedAuthority authority=new SimpleGrantedAuthority("ROLE_"+user.getRole());
		if("L".equals(user.isOcked())) {
			status=true;
		}else if("NL".equals(user.isOcked())){
			status=false;
		}
		granterList.add(authority);
		UserDetails userDetails=new UserDetailsClass(user.getUserName(), user.getPassword(),status, granterList);
		return userDetails;
	}

}
