package com.rubik.eds.web.management.ftpshare;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rubik.eds.common.constants.Constants;
import com.rubik.eds.entity.FtpShareLocalEntity;
import com.rubik.eds.service.FtpShareLocalFile;
import com.rubik.eds.service.management.ftpshare.FtpShareManagementService;
import com.rubik.support.security.SecurityUtils;
import com.rubik.support.service.OperatingLogService;

@Controller
@RequestMapping("/security/management")
public class FtpShareAction {

	/**
	 * 管理Service
	 */
	@Autowired
	private FtpShareManagementService ftpShareManagementService;
	@Autowired
	private FtpShareLocalFile ftpShareLocalFile;
	@Autowired
	private OperatingLogService operatingLogService;
	private StringBuffer comments;
	
	@RequestMapping("/ftpshare/ftpshare")
	public ModelAndView execute(FtpShareLocalEntity shareLocal, HttpServletRequest request){
		//获取远程配置参数
		shareLocal = ftpShareManagementService.findUnique();
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/management/ftpshare/ftpshare", "shareLocal", shareLocal);
		return modelAndView;
	}
	
	@RequestMapping("/ftpshare/save")
	@ResponseBody
	public Object saveAndUpdate(FtpShareLocalEntity shareLocal, String checkFtp, HttpServletRequest request){
		//检测FTP是否连通
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotEmpty(checkFtp)){
				ftpShareLocalFile.pingFtpConnectTest(shareLocal);
			}
			//保存共享目录
			File file = new File(shareLocal.getShareBasePath());
			if(!file.exists()){
				file.mkdirs();
			}
			//
			shareLocal.setOperationTime(new Date());
			shareLocal.setUserId(SecurityUtils.getCurrentUserId());
			//判断是否传入用户ID,是：编辑，否：新增
			if(shareLocal.getId() != null && !("".equals(shareLocal.getId()))){
				ftpShareManagementService.update(shareLocal);
				//保存操作日志
				comments = new StringBuffer();
				comments.append("更新FTP、共享配置参数信息：服务器");
				comments.append(shareLocal.getFtpAddress());
				comments.append(":");
				comments.append(shareLocal.getFtpPort());
				comments.append(")");
				operatingLogService.save(Constants.MODULE_MANAGEMENT_FTPSHARE, comments.toString());
			}else{
				ftpShareManagementService.insert(shareLocal);
				//保存操作日志
				comments = new StringBuffer();
				comments.append("更新FTP、共享配置参数信息：服务器");
				comments.append(shareLocal.getFtpAddress());
				comments.append(":");
				comments.append(shareLocal.getFtpPort());
				comments.append(")");
				operatingLogService.save(Constants.MODULE_MANAGEMENT_FTPSHARE, comments.toString());
			}
			map.put("result", true);
		} catch (Exception e) {
			map.put("result", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
}
