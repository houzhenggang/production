package com.rubik.eds.web.grass;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rubik.eds.common.constants.Constants;
import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbGrassMonitor;
import com.rubik.eds.entity.TbWeatherStation;
import com.rubik.eds.service.CommonService;
import com.rubik.eds.service.grass.GrassMonitorService;
import com.rubik.support.common.utils.DateUtils;
import com.rubik.support.security.SecurityUtils;
import com.rubik.support.service.OperatingLogService;

@Controller
@RequestMapping("/security/grass")
public class GrassMonitorAction {
	/**
	 * 管理Service
	 */
	@Autowired
	private GrassMonitorService grassMonitorService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private OperatingLogService operatingLogService;
	private StringBuffer comments;
	
	private List<TbWeatherStation> stationList;
	@RequestMapping("/grass")
	public ModelAndView execute(TbGrassMonitor grass, HttpServletRequest request){
		//定义并初始化(页码，页显数)
		
		List<TbGrassMonitor> grasss = new ArrayList<TbGrassMonitor>();
		grasss = grassMonitorService.findPageByRowBounds(SecurityUtils.getCurrentUserIdMap(), grass.getBounds());
		//获取记录总数
		grass.setTotalCount(grassMonitorService.getTotalCount());
		request.setAttribute("page", grass);
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/grass/grass", "grasss", grasss);
		return modelAndView;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(TbGrassMonitor grass , HttpServletRequest request){
		//用户具有的权限站点
		stationList = commonService.getStationListByUserId(SecurityUtils.getCurrentUserIdMap());
		request.setAttribute("stationList", stationList);
		//天然草场历史记录
		List<TbGrassMonitor> grasss = grassMonitorService.findAll();
		Set<String> grassNames = new HashSet<String>();
		for(TbGrassMonitor grassMonitor : grasss){
			grassNames.add(grassMonitor.getGrassName());
		}
		request.setAttribute("grassNames", grassNames);
		
		//判断是否传入用户ID,是：编辑，否：新增
		if(grass.getId() != null && !("".equals(grass.getId()))){
			grass = grassMonitorService.findById(grass.getId());
		}else{
			grass.setOperationTime(new Date());
		}
		
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/grass/grassEdit", "grass", grass);
		return modelAndView;
	}
	
	@RequestMapping("/save")
	public void saveAndUpdate(TbGrassMonitor grass, HttpServletRequest request, HttpServletResponse response){
		String result = "0";
		//grass.setOperationTime(new Date());
		//判断是否传入用户ID,是：编辑，否：新增
		if(grass.getId() != null && !("".equals(grass.getId()))){
			grass.setUserId(SecurityUtils.getCurrentUserId());
			grassMonitorService.update(grass);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("更新一条天然草场监测数据（站点");
			comments.append(grass.getStationId());
			comments.append("，录入时间：");
			comments.append(DateUtils.formatDate(grass.getOperationTime(), DateUtils.YYYY_MM_DD_HH_MM_SS));
			comments.append(")");
			operatingLogService.save(Constants.MODULE_GRASS, comments.toString());
			
			result = "1";
		}else{
			grass.setUserId(SecurityUtils.getCurrentUserId());
			grassMonitorService.insert(grass);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("新增一条天然草场监测数据（站点");
			comments.append(grass.getStationId());
			comments.append("，录入时间：");
			comments.append(DateUtils.formatDate(grass.getOperationTime(), DateUtils.YYYY_MM_DD_HH_MM_SS));
			comments.append(")");
			operatingLogService.save(Constants.MODULE_GRASS, comments.toString());
			
			result = "1";
		}
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Integer grassId, HttpServletRequest request){
		boolean result = false;
		if(grassId != null && !("".equals(grassId))){
			grassMonitorService.delete(grassId);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("删除一条天然草场监测数据");
			operatingLogService.save(Constants.MODULE_GRASS, comments.toString());
			
			result = true;
		}
		return result;
	}
	
	@RequestMapping("/reportHeader")
	public ModelAndView reportHeader(ReportHeader reportHeader, HttpServletRequest request){
		//用户具有的权限站点
		stationList = commonService.getStationListByUserId(SecurityUtils.getCurrentUserIdMap());
		List<ReportHeader> reporters = commonService.findAll();
		request.setAttribute("reporters", reporters);
		
		reportHeader.setReportStartDate(new Date());
		reportHeader.setReportDate(new Date());
		request.setAttribute("reportHeader", "reportHeader");
		
		return new ModelAndView("/grass/reportHeader", "stationList", stationList);
	}
	
	@RequestMapping("/createReport")
	public void reportHeader(ReportHeader reportHeader, HttpServletResponse response){
		//设置当前用户ID
		reportHeader.setCurrentUserId(SecurityUtils.getCurrentUserId());
		//获取导出报表数据
		List<TbGrassMonitor> grassMonitors = grassMonitorService.getGrassReportData(reportHeader);
		
		//保存操作日志
		comments = new StringBuffer();
		comments.append("生成天然草场监测报表数据（站点");
		comments.append(reportHeader.getReportStationId());
		comments.append("，填表人：");
		comments.append(reportHeader.getReporterName());
		comments.append(")");
		operatingLogService.save(Constants.MODULE_GRASS, comments.toString());
		
		//导出Excel表格
		grassMonitorService.createReport(response, reportHeader, grassMonitors);
	}
	
	/**
	 * 查询指定站点最新的记录
	 * @param stationId
	 */
	@RequestMapping("/getLasredGrassRecord")
	@ResponseBody
	public Object getLastedGrassRecord(String stationId){
		TbGrassMonitor grassMonitor = grassMonitorService.findLastedRecord(stationId);
		return grassMonitor;
	}
	
	@InitBinder 
	public void initBinder(WebDataBinder binder) { 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		dateFormat.setLenient(true); 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
	}
}
