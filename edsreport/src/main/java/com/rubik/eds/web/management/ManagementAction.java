package com.rubik.eds.web.management;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rubik.support.dao.TbSystemResourceDao;
import com.rubik.support.entity.TbSystemResource;
import com.rubik.support.security.SecurityUtils;
import com.rubik.support.security.service.SecurityService;

@Controller
@RequestMapping("/security/management")
public class ManagementAction {

	//用户安全service
	@Autowired
	private SecurityService securityService;
	
	@RequestMapping("/management")
	public ModelAndView execute(HttpServletRequest request){
		//定义参数map
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		paramMap.put("userId", SecurityUtils.getCurrentUserId());
		paramMap.put("resourceType", TbSystemResourceDao.MENU1_TYPE);
		List<TbSystemResource> userResources = securityService.getResourceUrlByUserId(paramMap);
		request.setAttribute("tabUrl", userResources.get(0).getUrl());
		request.setAttribute("tabName", userResources.get(0).getName());
		
		ModelAndView modelAndView = new ModelAndView("/management/management", "menuMap", userResources);
		return modelAndView;
	}
	
}
