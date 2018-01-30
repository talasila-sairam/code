package com.apps.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;





import org.springframework.stereotype.Component;

import com.apps.beans.Account;
import com.apps.dao.AccountDAO;
@Component("myDBAuthenticationService")
public class MyDBAuthenticationService implements UserDetailsService {
	boolean accountNonExpired = true;
	boolean credentialsNonExpired = true;
	boolean accountNonLocked = true;
	UserDetails userDetails=null;
	@Autowired
	private AccountDAO accountDAO;

	public MyDBAuthenticationService() {
		
	}

	@Override
	public UserDetails loadUserByUsername(String userName) {
		// TODO Auto-generated method stub
		Account account = accountDAO.findAccount(userName);
		if (account == null) {
			try {
				throw new Exception("User name not found:");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			String role = account.getUserRole();
			List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"
					+ role);
			grantList.add(authority);
			boolean enabled = account.isActive();
			 userDetails = (UserDetails) new User(
					account.getUserName(), //
					account.getPassword(), enabled, accountNonExpired, //
					credentialsNonExpired, accountNonLocked, grantList);
		
		return userDetails;
	}

}
