package com.rubik.eds.web.cropgrowth;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.rubik.eds.entity.TbCropGrowthMonitor;
import com.rubik.eds.entity.TbWeatherStation;
import com.rubik.eds.service.CommonService;
import com.rubik.eds.service.cropgrowth.CropGrowthMonitorService;
import com.rubik.support.common.utils.DateUtils;
import com.rubik.support.security.SecurityUtils;
import com.rubik.support.service.OperatingLogService;

@Controller
@RequestMapping("/security/cropgrowth")
public class CropGrowthMonitorAction {

	/**
	 * 沙尘管理Service
	 */
	@Autowired
	private CropGrowthMonitorService cropGrowthMonitorService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private OperatingLogService operatingLogService;
	private StringBuffer comments;
	
	private List<TbWeatherStation> stationList;
	@RequestMapping("/growth")
	public ModelAndView execute(TbCropGrowthMonitor cropGrowth, HttpServletRequest request){
		//定义并初始化(页码，页显数)
		
		List<TbCropGrowthMonitor> cropGrowths = new ArrayList<TbCropGrowthMonitor>();
		cropGrowths = cropGrowthMonitorService.findPageByRowBounds(SecurityUtils.getCurrentUserIdMap(), cropGrowth.getBounds());
		//获取记录总数
		cropGrowth.setTotalCount(cropGrowthMonitorService.getTotalCount());
		request.setAttribute("page", cropGrowth);
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/cropgrowth/growth", "cropGrowths", cropGrowths);
		return modelAndView;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(TbCropGrowthMonitor cropGrowth , HttpServletRequest request){
		//用户具有的权限站点
		stationList = commonService.getStationListByUserId(SecurityUtils.getCurrentUserIdMap());
		request.setAttribute("stationList", stationList);
		
		List<String> cropNames = cropGrowthMonitorService.getCropNames(SecurityUtils.getCurrentUserIdMap());
		request.setAttribute("cropNames", cropNames);
		
		//判断是否传入用户ID,是：编辑，否：新增
		if(cropGrowth.getId() != null && !("".equals(cropGrowth.getId()))){
			cropGrowth = cropGrowthMonitorService.findById(cropGrowth.getId());
		}else{
			cropGrowth.setOperationTime(new Date());
		}
		
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/cropgrowth/growthEdit", "cropGrowth", cropGrowth);
		return modelAndView;
	}
	
	@RequestMapping("/save")
	public void saveAndUpdate(TbCropGrowthMonitor cropGrowth, HttpServletRequest request, HttpServletResponse response){
		String result = "0";
		//cropGrowth.setOperationTime(new Date());
		//判断是否传入用户ID,是：编辑，否：新增
		if(cropGrowth.getId() != null && !("".equals(cropGrowth.getId()))){
			cropGrowth.setUserId(SecurityUtils.getCurrentUserId());
			cropGrowthMonitorService.update(cropGrowth);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("更新一条农作物生长状况监测数据（站点");
			comments.append(cropGrowth.getStationId());
			comments.append("，录入时间：");
			comments.append(DateUtils.formatDate(cropGrowth.getOperationTime(), DateUtils.YYYY_MM_DD_HH_MM_SS));
			comments.append(")");
			operatingLogService.save(Constants.MODULE_CROP_GROWTH, comments.toString());
			
			result = "1";
		}else{
			cropGrowth.setUserId(SecurityUtils.getCurrentUserId());
			cropGrowthMonitorService.insert(cropGrowth);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("新增一条农作物生长状况监测数据（站点");
			comments.append(cropGrowth.getStationId());
			comments.append("，录入时间：");
			comments.append(DateUtils.formatDate(cropGrowth.getOperationTime(), DateUtils.YYYY_MM_DD_HH_MM_SS));
			comments.append(")");
			operatingLogService.save(Constants.MODULE_CROP_GROWTH, comments.toString());
			
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
	public Object delete(Integer cropGrowthId, HttpServletRequest request){
		boolean result = false;
		if(cropGrowthId != null && !("".equals(cropGrowthId))){
			cropGrowthMonitorService.delete(cropGrowthId);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("删除一条农作物生长状况监测数据");
			operatingLogService.save(Constants.MODULE_CROP_GROWTH, comments.toString());
			
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
		
		return new ModelAndView("/cropgrowth/reportHeader", "stationList", stationList);
	}
	
	@RequestMapping("/createReport")
	public void reportHeader(ReportHeader reportHeader, HttpServletResponse response){
		//设置当前用户ID
		reportHeader.setCurrentUserId(SecurityUtils.getCurrentUserId());
		//获取导出报表数据
		List<TbCropGrowthMonitor> cropGrowthMonitors = cropGrowthMonitorService.getCropGrowthReportData(reportHeader);
		
		//保存操作日志
		comments = new StringBuffer();
		comments.append("生成农作物生长状况监测报表数据（站点");
		comments.append(reportHeader.getReportStationId());
		comments.append("，填表人：");
		comments.append(reportHeader.getReporterName());
		comments.append(")");
		operatingLogService.save(Constants.MODULE_CROP_GROWTH, comments.toString());
		
		//导出Excel表格
		cropGrowthMonitorService.createReport(response, reportHeader, cropGrowthMonitors);
	}
	
	@InitBinder 
	public void initBinder(WebDataBinder binder) { 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		dateFormat.setLenient(true); 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
	}
}
