/**
 * 
 */
package com.rubik.support.security.entity;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.rubik.support.entity.TbSystemUser;

/**
 * @author Administrator
 *
 */
public class UserDetail extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TbSystemUser user = null;
 
	public UserDetail(String username, String password,
			Set<GrantedAuthority> authorities, TbSystemUser user)  throws IllegalArgumentException{
		super(username, password, authorities);
		this.user = user;
	}

	public TbSystemUser getUser() {
		return user;
	}

	public void setUser(TbSystemUser user) {
		this.user = user;
	}
	
	
}
