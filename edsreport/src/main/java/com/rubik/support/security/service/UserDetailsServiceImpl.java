/**
 * 
 */
package com.rubik.support.security.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.rubik.support.entity.TbSystemAuthority;
import com.rubik.support.entity.TbSystemUser;
import com.rubik.support.security.entity.UserDetail;

/**
 * @author Administrator
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService {

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Autowired
	@Qualifier("securityService")
	private SecurityService securityService;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		TbSystemUser user = securityService.getUserByLoginName(username);
		UserDetails userdetails = new UserDetail(username, user.getPassword(), obtainGrantedAuthorities(user), user);
		return userdetails;
	}
	
	public Set<GrantedAuthority> obtainGrantedAuthorities(TbSystemUser user){
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		List<TbSystemAuthority> authorityList = securityService.getAuthoritiesByUser(user);
		for(TbSystemAuthority authority : authorityList){
			authSet.add(new SimpleGrantedAuthority(authority.getAuthority()));
		}
		return authSet;
	}

}
