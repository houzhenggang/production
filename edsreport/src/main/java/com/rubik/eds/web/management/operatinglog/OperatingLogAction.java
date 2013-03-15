package com.rubik.eds.web.management.operatinglog;

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
import com.rubik.support.entity.TbGbOperatingLog;
import com.rubik.support.service.OperatingLogService;

@Controller
@RequestMapping("/security/management")
public class OperatingLogAction {

	/**
	 * 站点管理Service
	 */
	@Autowired
	private OperatingLogService operatingLogService;
	private StringBuffer comments;
	@RequestMapping("/operatinglog/log")
	public ModelAndView execute(TbGbOperatingLog operatinglog, HttpServletRequest request){
		//定义并初始化(页码，页显数)
		
		List<TbGbOperatingLog> logList = new ArrayList<TbGbOperatingLog>();
		logList = operatingLogService.getOperatingLogByRowBounds(operatinglog.getBounds());
		//获取记录总数
		operatinglog.setTotalCount(operatingLogService.getTotalCount());
		request.setAttribute("page", operatinglog);
		
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/management/operatinglog/log", "logList", logList);
		return modelAndView;
	}
	
	
	@RequestMapping("/operatinglog/delete")
	@ResponseBody
	public Object delete(String id, HttpServletRequest request){
		boolean result = false;
		if(id != null){
			List<String> ids =Arrays.asList(id.split(","));
			operatingLogService.delete(ids);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("删除");
			comments.append(ids.size());
			comments.append("条操作记录");
			operatingLogService.save(Constants.MODULE_MANAGEMENT_OPERATINGLOG, comments.toString());
			
			result = true;
		}
		return result;
	}
}
