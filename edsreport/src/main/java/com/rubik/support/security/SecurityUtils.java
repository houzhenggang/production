package com.rubik.support.security;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import com.rubik.support.entity.TbSystemUser;
import com.rubik.support.security.entity.UserDetail;


public class SecurityUtils {
	
	public static Map<Object, Object> getCurrentUserIdMap(){
		Map<Object, Object> params = new HashMap<Object, Object>();
		if("super".equals(getCurrentUser().getUserRole())){
			params.put("userId", null);
		}else{
			params.put("userId", getCurrentUserId());
		}
		return params;
	}
	
	public static TbSystemUser getCurrentUser() {
		UserDetail userDetail = (UserDetail) getCurrentSecurityUser();
		if (userDetail == null)
			return null;
		return userDetail.getUser();
	}

	public static String getCurrentUserName() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth == null)
			return "";
		return auth.getName();
	}

	public static String getCurrentUserNickname() {
		TbSystemUser user = getCurrentUser();
	    if (user == null)
	      return "";
	    return user.getNickname();
	  }
	
	public static Integer getCurrentUserId() {
		TbSystemUser user = getCurrentUser();
		if (user == null) {
			return null;
		}
		return user.getId();
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends User> T getCurrentSecurityUser() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth == null) {
			return null;
		}
		Object principal = auth.getPrincipal();
		if (principal == null)
			return null;
		if (principal instanceof String) {
			TbSystemUser user = new TbSystemUser();
			user.setUsername((String) principal);
			user.setPassword((String) principal);
			principal = new UserDetail(user.getUsername(), user.getPassword(),new HashSet<GrantedAuthority>(), user);
		}
		return (T) ((User) principal);
	}
}
