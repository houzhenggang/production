/**
 * 
 */
package com.rubik.eds.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rubik.support.dao.TbSystemResourceDao;
import com.rubik.support.entity.TbSystemResource;
import com.rubik.support.security.SecurityUtils;
import com.rubik.support.security.service.SecurityService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/")
public class LoginAction {
	//用户安全service
	@Autowired
	private SecurityService securityService;
	
	@RequestMapping("/login")
	public String Login(){
		return "login";
	}
	
	@RequestMapping("/security/homepage")
	public String homepage(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response){
		//定义参数map
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("userId", SecurityUtils.getCurrentUserId());
		paramMap.put("resourceType", TbSystemResourceDao.MENU_TYPE);
		List<TbSystemResource> userResources = securityService.getResourceUrlByUserId(paramMap);
		request.setAttribute("menuMap", userResources);
		request.setAttribute("user", SecurityUtils.getCurrentUser());
		return "homepage";
	}
}
