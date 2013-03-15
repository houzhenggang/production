/**
 * 水体监测数据报表Action
 */
package com.rubik.eds.web.water;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import com.rubik.eds.entity.TbWaterMonitor;
import com.rubik.eds.entity.TbWeatherStation;
import com.rubik.eds.service.CommonService;
import com.rubik.eds.service.water.WaterMonitorService;
import com.rubik.support.common.utils.DateUtils;
import com.rubik.support.security.SecurityUtils;
import com.rubik.support.service.OperatingLogService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/security/water")
public class WaterMonitorAction{
	
	/**
	 * 站点管理Service
	 */
	@Autowired
	private WaterMonitorService waterMonitorService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private OperatingLogService operatingLogService;
	private StringBuffer comments;
	
	private List<TbWeatherStation> stationList;
	@RequestMapping("/water")
	public ModelAndView execute(TbWaterMonitor water, HttpServletRequest request){
		//定义并初始化(页码，页显数)
		List<TbWaterMonitor> waters = new ArrayList<TbWaterMonitor>();
		waters = waterMonitorService.findPageByRowBounds(SecurityUtils.getCurrentUserIdMap(), water.getBounds());
		//获取记录总数
		water.setTotalCount(waterMonitorService.getTotalCount());
		request.setAttribute("page", water);
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/water/water", "waters", waters);
		return modelAndView;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(TbWaterMonitor water , HttpServletRequest request){
		//用户具有的权限站点
		stationList = commonService.getStationListByUserId(SecurityUtils.getCurrentUserIdMap());
		request.setAttribute("stationList", stationList);
		//已登记的水体名称
		List<TbWaterMonitor> waters = waterMonitorService.findAll(SecurityUtils.getCurrentUserIdMap());
		Map<Object, Set<Object>> datadicts = new HashMap<Object, Set<Object>>();
		Set<Object> waterNames = new HashSet<Object>();
		Set<Object> fetchLongitudes = new HashSet<Object>();
		Set<Object> fetchLatitudes = new HashSet<Object>();
		Set<Object> turnLongitudes = new HashSet<Object>();
		Set<Object> turnLatitudes = new HashSet<Object>();
		Set<Object> waterColors = new HashSet<Object>();
		
		for(TbWaterMonitor tbWaterMonitor : waters){
			waterNames.add(tbWaterMonitor.getWaterName());
			fetchLongitudes.add(tbWaterMonitor.getFetchLongitude());
			fetchLatitudes.add(tbWaterMonitor.getFetchLatitude());
			turnLongitudes.add(tbWaterMonitor.getTurnLongitude());
			turnLatitudes.add(tbWaterMonitor.getTurnLatitude());
			waterColors.add(tbWaterMonitor.getWaterColor());
		}
		
		datadicts.put("waterNames", waterNames);
		datadicts.put("fetchLongitudes", fetchLongitudes);
		datadicts.put("fetchLatitudes", fetchLatitudes);
		datadicts.put("turnLongitudes", turnLongitudes);
		datadicts.put("turnLatitudes", turnLatitudes);
		datadicts.put("waterColors", waterColors);
		
		request.setAttribute("datadicts", datadicts);
		
		//判断是否传入用户ID,是：编辑，否：新增
		if(water.getId() != null && !("".equals(water.getId()))){
			water = waterMonitorService.findById(water.getId());
		}else{
			water.setOperationTime(new Date());
		}
		
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/water/waterEdit", "water", water);
		return modelAndView;
	}
	
	@RequestMapping("/save")
	public void saveAndUpdate(TbWaterMonitor water, HttpServletRequest request, HttpServletResponse response){
		String result = "0";
		//water.setOperationTime(new Date());
		//判断是否传入用户ID,是：编辑，否：新增
		if(water.getId() != null && !("".equals(water.getId()))){
			water.setUserId(SecurityUtils.getCurrentUserId());
			waterMonitorService.update(water);
			//保存操作日志
			comments = new StringBuffer();
			comments.append("更新一条水体报表数据（站点");
			comments.append(water.getStationId());
			comments.append("，录入时间：");
			comments.append(DateUtils.formatDate(water.getOperationTime(), DateUtils.YYYY_MM_DD_HH_MM_SS));
			comments.append(")");
			operatingLogService.save(Constants.MODULE_WATER, comments.toString());
			
			result = "1";
		}else{
			water.setUserId(SecurityUtils.getCurrentUserId());
			waterMonitorService.insert(water);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("新增一条水体报表数据（站点");
			comments.append(water.getStationId());
			comments.append("，录入时间：");
			comments.append(DateUtils.formatDate(water.getOperationTime(), DateUtils.YYYY_MM_DD_HH_MM_SS));
			comments.append(")");
			operatingLogService.save(Constants.MODULE_WATER, comments.toString());
			
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
	public Object delete(Integer waterId, HttpServletRequest request){
		boolean result = false;
		if(waterId != null && !("".equals(waterId))){
			waterMonitorService.delete(waterId);
			//保存操作日志
			comments = new StringBuffer();
			comments.append("删除一条水体报表数据");
			operatingLogService.save(Constants.MODULE_WATER, comments.toString());
			
			result = true;
		}
		return result;
	}
	
	@RequestMapping("/reportHeader")
	public ModelAndView reportHeader(ReportHeader reportHeader, HttpServletRequest request){
		//用户具有的权限站点
		stationList = commonService.getStationListByUserId(SecurityUtils.getCurrentUserIdMap());
		
		//已登记的水体名称
		List<TbWaterMonitor> waterNames = waterMonitorService.getWaterNames(SecurityUtils.getCurrentUserIdMap());
		request.setAttribute("waterNames", waterNames);
		List<ReportHeader> reporters = commonService.findAll();
		request.setAttribute("reporters", reporters);
		
		//
		reportHeader.setReportStartDate(new Date());
		reportHeader.setReportDate(new Date());
		request.setAttribute("reportHeader", "reportHeader");
		
		
		return new ModelAndView("/water/reportHeader", "stationList", stationList);
	}
	
	@RequestMapping("/createReport")
	public void reportHeader(ReportHeader reportHeader, HttpServletResponse response){
		//设置当前用户ID
		reportHeader.setCurrentUserId(SecurityUtils.getCurrentUserId());
		//获取导出报表数据
		List<TbWaterMonitor> waterMonitors = waterMonitorService.getWaterReportData(reportHeader);
		//保存报表导出记录
		commonService.insertReportRecord(reportHeader);
		
		//保存操作日志
		comments = new StringBuffer();
		comments.append("生成水体报表数据（站点");
		comments.append(reportHeader.getReportStationId());
		comments.append("，填表人：");
		comments.append(reportHeader.getReporterName());
		comments.append(")");
		operatingLogService.save(Constants.MODULE_WATER, comments.toString());
		
		//导出Excel表格
		waterMonitorService.createReport(response, reportHeader, waterMonitors);
		
	}
	
	@InitBinder 
	public void initBinder(WebDataBinder binder) { 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		dateFormat.setLenient(true); 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
	} 
}
