package com.rubik.eds.web.management.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rubik.eds.common.constants.Constants;
import com.rubik.eds.entity.SelectOptions;
import com.rubik.eds.entity.TbWeatherStation;
import com.rubik.eds.service.management.user.UserManagementService;
import com.rubik.support.common.constants.MyConstants;
import com.rubik.support.entity.TbSystemRole;
import com.rubik.support.entity.TbSystemUser;
import com.rubik.support.security.SecurityUtils;
import com.rubik.support.security.service.SecurityService;
import com.rubik.support.service.DatadictService;
import com.rubik.support.service.OperatingLogService;

@Controller
@RequestMapping("/security/management")
public class UserAction {

	/**
	 * 用户管理Service
	 */
	@Autowired
	private UserManagementService userManagementService;
	@Autowired
	private DatadictService datadictService;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private OperatingLogService operatingLogService;
	private StringBuffer comments;
	
	@RequestMapping("/user/user")
	public ModelAndView execute(TbSystemUser user, HttpServletRequest request){
		//定义并初始化(页码，页显数)
		request.setAttribute("userRoles", SelectOptions.getUserRoles());
		
		List<TbSystemUser> users = new ArrayList<TbSystemUser>();
		users = userManagementService.getUserByRowBounds(user.getBounds());
		//获取记录总数
		user.setTotalCount(userManagementService.getTotalCount());
		request.setAttribute("page", user);
		
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/management/user/user", "userList", users);
		return modelAndView;
	}
	
	@RequestMapping("/user/edit")
	public ModelAndView edit(TbSystemUser user , HttpServletRequest request){
		request.setAttribute("roleSelects", SelectOptions.getUserRoles());
		request.setAttribute("userRole", SecurityUtils.getCurrentUser().getUserRole());
		//所有权限列表
		List<TbSystemRole> roles = userManagementService.getAllOptionsRole();
		request.setAttribute("roles", roles);
		
		//所有站点列表
		List<TbWeatherStation> stations = userManagementService.getAllStations();
		request.setAttribute("stations", stations);
		
		//判断是否传入用户ID,是：编辑，否：新增
		if(user.getId() != null && !("".equals(user.getId()))){
			user = userManagementService.getUserById(user.getId());
		}
		List<Integer> userRoles = new ArrayList<Integer>();
		userRoles = userManagementService.getRolesByUserId(user.getId());
		request.setAttribute("userRoles", userRoles);
		
		List<String> userStations = new ArrayList<String>();
		userStations = userManagementService.getStationsByUserId(user.getId());
		request.setAttribute("userStations", userStations);
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/management/user/userEdit", "user", user);
		return modelAndView;
	}
	
	@RequestMapping("/user/save")
	public void saveAndUpdate(TbSystemUser user, String[] roles, String[] stations,
			HttpServletRequest request, HttpServletResponse response){
		String result = "0";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mainId",user.getId());
		paramMap.put("roles", roles);
		paramMap.put("stations", stations);
		//判断是否传入用户ID,是：编辑，否：新增
		if(user.getId() != null && !("".equals(user.getId()))){
			userManagementService.update(user);
			userManagementService.deleteRoleById(user.getId());
			userManagementService.deleteStationById(user.getId());
			userManagementService.saveUserRole(paramMap);
			if(stations != null && stations.length > 0){
				userManagementService.saveUserStation(paramMap);
			}
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("更新一条用户信息记录（用户：");
			comments.append(user.getUsername());
			comments.append("，用户名：");
			comments.append(user.getNickname());
			comments.append(")");
			operatingLogService.save(Constants.MODULE_MANAGEMENT_USER, comments.toString());
			
			result = "1";
		}else{
			user.setPassword(datadictService.getValueById(MyConstants.PASSWORD_CODE).getValue());
			userManagementService.insert(user);
			userManagementService.saveUserRole(paramMap);
			if(stations != null && stations.length > 0){
				userManagementService.saveUserStation(paramMap);
			}
			//保存操作日志
			comments = new StringBuffer();
			comments.append("新增一条用户信息记录（用户：");
			comments.append(user.getUsername());
			comments.append("，用户名：");
			comments.append(user.getNickname());
			comments.append(")");
			operatingLogService.save(Constants.MODULE_MANAGEMENT_USER, comments.toString());
			
			result = "1";
		}
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/user/delete")
	@ResponseBody
	public Object delete(Integer id, HttpServletRequest request){
		boolean result = false;
		if(id != null && !("".equals(id))){
			userManagementService.delete(id);
			userManagementService.deleteRoleById(id);
			//保存操作日志
			comments = new StringBuffer();
			comments.append("删除一条用户信息记录");
			operatingLogService.save(Constants.MODULE_MANAGEMENT_USER, comments.toString());
			
			result = true;
		}
		return result;
	}
	
	@RequestMapping("/user/checkUsername")
	public void checkUsername(String username, HttpServletResponse response){
		try {
			if(securityService.getUserByLoginName(username) == null){
				response.getWriter().write("1");
			}else{
				//用户名已存在
				response.getWriter().write("0");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
