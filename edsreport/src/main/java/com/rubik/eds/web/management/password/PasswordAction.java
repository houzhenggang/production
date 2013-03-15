package com.rubik.eds.web.management.password;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rubik.eds.common.constants.Constants;
import com.rubik.eds.service.management.user.UserManagementService;
import com.rubik.support.common.utils.Md5PasswordEncode;
import com.rubik.support.entity.TbSystemUser;
import com.rubik.support.security.SecurityUtils;
import com.rubik.support.service.OperatingLogService;

@Controller
@RequestMapping("/security/management")
public class PasswordAction {

	/**
	 * 站点管理Service
	 */
	@Autowired
	private UserManagementService userManagementService;
	@Autowired
	private OperatingLogService operatingLogService;
	private StringBuffer comments;
	
	@RequestMapping("/password/password")
	public ModelAndView execute(){
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/management/password/password");
		return modelAndView;
	}
	
	@RequestMapping("/password/change")
	public void changePassword(String oldPassword, String newPassword, HttpServletResponse response){
		try {
			//获取当前用户信息
			TbSystemUser user = SecurityUtils.getCurrentUser();
			if(user.getPassword().equals(Md5PasswordEncode.encodePassword(oldPassword))){
				//设置新密码
				user.setPassword(Md5PasswordEncode.encodePassword(newPassword));
				userManagementService.update(user);
				
				//操作记录
				comments = new StringBuffer();
				comments.append("修改用户密码并修改成功");
				operatingLogService.save(Constants.MODULE_MANAGEMENT_PASSWORD, comments.toString());
				response.getWriter().write("1");
			}else{
				//操作记录
				comments = new StringBuffer();
				comments.append("修改用户密码并修改失败");
				operatingLogService.save(Constants.MODULE_MANAGEMENT_PASSWORD, comments.toString());
				response.getWriter().write("0");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/password/checkPassword")
	public void checkOldPassword(String oldPassword, HttpServletResponse response){
		try {
			if(SecurityUtils.getCurrentUser().getPassword().equals(Md5PasswordEncode.encodePassword(oldPassword))){
				response.getWriter().write("1");
			}else{
				//旧密码与现有密码不一致
				response.getWriter().write("0");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
