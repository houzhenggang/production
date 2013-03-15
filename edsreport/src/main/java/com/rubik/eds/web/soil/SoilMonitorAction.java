package com.rubik.eds.web.soil;

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
import com.rubik.eds.common.utils.CommonUtils;
import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbSoilErosionMonitor;
import com.rubik.eds.entity.TbSoilErosionReport;
import com.rubik.eds.entity.TbWeatherStation;
import com.rubik.eds.service.CommonService;
import com.rubik.eds.service.soil.SoilErosionReportService;
import com.rubik.eds.service.soil.SoilMonitorService;
import com.rubik.support.common.utils.DateUtils;
import com.rubik.support.security.SecurityUtils;
import com.rubik.support.service.OperatingLogService;

@Controller
@RequestMapping("/security/soil")
public class SoilMonitorAction {
	/**
	 * 管理Service
	 */
	@Autowired
	private SoilMonitorService soilMonitorService;
	@Autowired
	private SoilErosionReportService soilErosionReportService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private OperatingLogService operatingLogService;
	private StringBuffer comments;
	
	private List<TbWeatherStation> stationList;
	@RequestMapping("/soil")
	public ModelAndView execute(TbSoilErosionMonitor soil, HttpServletRequest request){
		//定义并初始化(页码，页显数)
		
		List<TbSoilErosionMonitor> soils = new ArrayList<TbSoilErosionMonitor>();
		soils = soilMonitorService.findPageByRowBounds(SecurityUtils.getCurrentUserIdMap(), soil.getBounds());
		//获取记录总数
		soil.setTotalCount(soilMonitorService.getTotalCount());
		request.setAttribute("page", soil);
		
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/soil/soil", "soils", soils);
		return modelAndView;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(TbSoilErosionMonitor soil , HttpServletRequest request){
		//用户具有的权限站点
		stationList = commonService.getStationListByUserId(SecurityUtils.getCurrentUserIdMap());
		request.setAttribute("stationList", stationList);
		
		
		//判断是否传入用户ID,是：编辑，否：新增
		if(soil.getId() != null && !("".equals(soil.getId()))){
			soil = soilMonitorService.findById(soil.getId());
		}else{
			soil.setOperationTime(new Date());
		}
		
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/soil/soilEdit", "soil", soil);
		return modelAndView;
	}
	
	@RequestMapping("/save")
	public void saveAndUpdate(TbSoilErosionMonitor soil, HttpServletRequest request, HttpServletResponse response){
		String result = "0";
		//soil.setOperationTime(new Date());
		//删除相同月份的监测数据
		soil.setSoilMonitorDate(CommonUtils.getLastdayOfLastmonth(soil.getOperationTime()));
		soilMonitorService.deleteByTime(soil);
		soil.setUserId(SecurityUtils.getCurrentUserId());
		soilMonitorService.insert(soil);
		//保存操作日志
		comments = new StringBuffer();
		comments.append("编辑一条土壤监测数据（站点");
		comments.append(soil.getStationId());
		comments.append("，录入时间：");
		comments.append(DateUtils.formatDate(soil.getOperationTime(), DateUtils.YYYY_MM_DD_HH_MM_SS));
		comments.append(")");
		operatingLogService.save(Constants.MODULE_SOIL, comments.toString());
		result = "1";
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Integer soilId, HttpServletRequest request){
		boolean result = false;
		if(soilId != null && !("".equals(soilId))){
			soilMonitorService.delete(soilId);
			//保存操作日志
			comments = new StringBuffer();
			comments.append("删除一条土壤监测数据");
			operatingLogService.save(Constants.MODULE_SOIL, comments.toString());
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
		
		return new ModelAndView("/soil/reportHeader", "stationList", stationList);
	}
	
	@RequestMapping("/createReport")
	public void reportHeader(ReportHeader reportHeader, HttpServletResponse response){
		//设置当前用户ID
		reportHeader.setCurrentUserId(SecurityUtils.getCurrentUserId());
		//获取导出报表数据
		List<TbSoilErosionReport> erosionReports = soilMonitorService.getSoilErosionReportData(reportHeader);
		
		TbSoilErosionReport erosionReport = soilMonitorService.generateReportDate(erosionReports, reportHeader);
		if(erosionReport != null){
			erosionReport.setStationId(reportHeader.getReportStationId());
			erosionReport.setStationName(reportHeader.getReportStationName());
			erosionReport.setReporterName(reportHeader.getReporterName());
			erosionReport.setReportDate(reportHeader.getReportDate());
			
			//先清理数据
			soilErosionReportService.deleteByTime(erosionReport);
			TbSoilErosionReport tempReport = erosionReport;
			//如果查询出记录则把报表记录插入数据库
			soilErosionReportService.insert(tempReport);
		}
		List<TbSoilErosionReport> soilErosionReports = soilErosionReportService.getSoilReports(reportHeader);
		//保存操作日志
		comments = new StringBuffer();
		comments.append("生成土壤监测报表数据（站点");
		comments.append(reportHeader.getReportStationId());
		comments.append("，填表人：");
		comments.append(reportHeader.getReporterName());
		comments.append(")");
		operatingLogService.save(Constants.MODULE_SOIL, comments.toString());
		
		//导出Excel表格
		soilMonitorService.createReport(response, reportHeader, soilErosionReports);
	}
	
	/**
	 * 历史土壤监测数据列表
	 * @param soilReport
	 * @return
	 */
	@RequestMapping("/soilReport")
	public ModelAndView soilReportPage(TbSoilErosionReport soilReport, HttpServletRequest request){
		//定义并初始化(页码，页显数)
		
		List<TbSoilErosionReport> soilReports = new ArrayList<TbSoilErosionReport>();
		soilReports = soilErosionReportService.findPageByRowBounds(SecurityUtils.getCurrentUserIdMap(), soilReport.getBounds());
		//获取记录总数
		soilReport.setTotalCount(soilErosionReportService.getTotalCount());
		request.setAttribute("page", soilReport);
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/soil/soilReport", "soilReports", soilReports);
		return modelAndView;
	}
	
	@RequestMapping("/deleteReport")
	@ResponseBody
	public Object deleteReport(Integer soilReportId, HttpServletRequest request){
		boolean result = false;
		if(soilReportId != null && !("".equals(soilReportId))){
			soilErosionReportService.delete(soilReportId);
			//保存操作日志
			comments = new StringBuffer();
			comments.append("删除一条历史土壤监测报表数据");
			operatingLogService.save(Constants.MODULE_SOIL, comments.toString());
			
			result = true;
		}
		return result;
	}
	
	@InitBinder 
	public void initBinder(WebDataBinder binder) { 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		dateFormat.setLenient(true); 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
	}
}
