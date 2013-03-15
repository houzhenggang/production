package com.rubik.eds.web.management.loginlog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rubik.eds.common.constants.Constants;
import com.rubik.support.entity.TbGbLoginLog;
import com.rubik.support.service.LoginLogService;
import com.rubik.support.service.OperatingLogService;

@Controller
@RequestMapping("/security/management")
public class LoginLogAction {

	/**
	 * 站点管理Service
	 */
	@Autowired
	private LoginLogService loginLogService;
	@Autowired
	private OperatingLogService operatingLogService;
	private StringBuffer comments;
	
	@RequestMapping("/loginlog/log")
	public ModelAndView execute(TbGbLoginLog loginlog, HttpServletRequest request){
		//定义并初始化(页码，页显数)
		
		List<TbGbLoginLog> logList = new ArrayList<TbGbLoginLog>();
		logList = loginLogService.getLoginLogByRowBounds(loginlog.getBounds());
		//获取记录总数
		loginlog.setTotalCount(loginLogService.getTotalCount());
		request.setAttribute("page", loginlog);
		
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/management/loginlog/log", "logList", logList);
		return modelAndView;
	}
	
	
	@RequestMapping("/loginlog/delete")
	@ResponseBody
	public Object delete(String id, HttpServletRequest request){
		boolean result = false;
		if(id != null){
			List<String> ids =Arrays.asList(id.split(","));
			loginLogService.delete(ids);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("删除");
			comments.append(ids.size());
			comments.append("条用户登录记录");
			operatingLogService.save(Constants.MODULE_MANAGEMENT_LOGINLOG, comments.toString());
			
			result = true;
		}
		return result;
	}
}
