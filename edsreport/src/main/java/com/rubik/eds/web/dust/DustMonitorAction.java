package com.rubik.eds.web.dust;

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
import com.rubik.eds.entity.TbDustMonitor;
import com.rubik.eds.entity.TbWeatherStation;
import com.rubik.eds.service.CommonService;
import com.rubik.eds.service.dust.DustMonitorService;
import com.rubik.support.common.utils.DateUtils;
import com.rubik.support.security.SecurityUtils;
import com.rubik.support.service.OperatingLogService;

@Controller
@RequestMapping("/security/dust")
public class DustMonitorAction {

	/**
	 * 沙尘管理Service
	 */
	@Autowired
	private DustMonitorService dustMonitorService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private OperatingLogService operatingLogService;
	private StringBuffer comments;
	
	private List<TbWeatherStation> stationList;
	@RequestMapping("/dust")
	public ModelAndView execute(TbDustMonitor dust, HttpServletRequest request){
		//定义并初始化(页码，页显数)
		
		List<TbDustMonitor> dusts = new ArrayList<TbDustMonitor>();
		dusts = dustMonitorService.findPageByRowBounds(SecurityUtils.getCurrentUserIdMap(), dust.getBounds());
		//获取记录总数
		dust.setTotalCount(dustMonitorService.getTotalCount());
		request.setAttribute("page", dust);
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/dust/dust", "dusts", dusts);
		return modelAndView;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(TbDustMonitor dust , HttpServletRequest request){
		//用户具有的权限站点
		stationList = commonService.getStationListByUserId(SecurityUtils.getCurrentUserIdMap());
		request.setAttribute("stationList", stationList);
		
		
		//判断是否传入用户ID,是：编辑，否：新增
		if(dust.getId() != null && !("".equals(dust.getId()))){
			dust = dustMonitorService.findById(dust.getId());
		}else{
			dust.setOperationTime(new Date());
		}
		
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/dust/dustEdit", "dust", dust);
		return modelAndView;
	}
	
	@RequestMapping("/save")
	public void saveAndUpdate(TbDustMonitor dust, HttpServletRequest request, HttpServletResponse response){
		String result = "0";
		//dust.setOperationTime(new Date());
		//判断是否传入用户ID,是：编辑，否：新增
		if(dust.getId() != null && !("".equals(dust.getId()))){
			dust.setUserId(SecurityUtils.getCurrentUserId());
			dustMonitorService.update(dust);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("更新一条沙尘监测数据（站点");
			comments.append(dust.getStationId());
			comments.append("，录入时间：");
			comments.append(DateUtils.formatDate(dust.getOperationTime(), DateUtils.YYYY_MM_DD_HH_MM_SS));
			comments.append(")");
			operatingLogService.save(Constants.MODULE_DUST, comments.toString());
			
			result = "1";
		}else{
			dust.setUserId(SecurityUtils.getCurrentUserId());
			dustMonitorService.insert(dust);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("新增一条沙尘监测数据（站点");
			comments.append(dust.getStationId());
			comments.append("，录入时间：");
			comments.append(DateUtils.formatDate(dust.getOperationTime(), DateUtils.YYYY_MM_DD_HH_MM_SS));
			comments.append(")");
			operatingLogService.save(Constants.MODULE_DUST, comments.toString());
			
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
	public Object delete(Integer dustId, HttpServletRequest request){
		boolean result = false;
		if(dustId != null && !("".equals(dustId))){
			dustMonitorService.delete(dustId);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("删除一条沙尘监测数据");
			operatingLogService.save(Constants.MODULE_DUST, comments.toString());
			
			result = true;
		}
		return result;
	}
	
	@RequestMapping("/reportHeader")
	public ModelAndView reportHeader(ReportHeader reportHeader,  HttpServletRequest request){
		//用户具有的权限站点
		stationList = commonService.getStationListByUserId(SecurityUtils.getCurrentUserIdMap());
		
		List<ReportHeader> reporters = commonService.findAll();
		request.setAttribute("reporters", reporters);
		
		reportHeader.setReportStartDate(new Date());
		reportHeader.setReportDate(new Date());
		request.setAttribute("reportHeader", "reportHeader");
		
		return new ModelAndView("/dust/reportHeader", "stationList", stationList);
	}
	
	@RequestMapping("/createReport")
	public void reportHeader(ReportHeader reportHeader, HttpServletResponse response){
		//设置当前用户ID
		reportHeader.setCurrentUserId(SecurityUtils.getCurrentUserId());
		//获取导出报表数据
		List<TbDustMonitor> dustMonitors = dustMonitorService.getDustReportData(reportHeader);
		
		//保存操作日志
		comments = new StringBuffer();
		comments.append("生成沙尘监测报表数据（站点");
		comments.append(reportHeader.getReportStationId());
		comments.append("，填表人：");
		comments.append(reportHeader.getReporterName());
		comments.append(")");
		operatingLogService.save(Constants.MODULE_DUST, comments.toString());
		
		//导出Excel表格
		dustMonitorService.createReport(response, reportHeader, dustMonitors);
	}
	
	@InitBinder 
	public void initBinder(WebDataBinder binder) { 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
