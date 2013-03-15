package com.rubik.eds.web.dunemove;

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
import com.rubik.eds.entity.SelectOptions;
import com.rubik.eds.entity.TbDuneMoveMonitor;
import com.rubik.eds.entity.TbWeatherStation;
import com.rubik.eds.service.CommonService;
import com.rubik.eds.service.dunemove.DuneMoveMonitorService;
import com.rubik.eds.service.dunemove.DuneMoveReportService;
import com.rubik.support.common.utils.DateUtils;
import com.rubik.support.security.SecurityUtils;
import com.rubik.support.service.OperatingLogService;

@Controller
@RequestMapping("/security/dunemove")
public class DuneMoveMonitorAction {
	/**
	 * 管理Service
	 */
	@Autowired
	private DuneMoveMonitorService duneMoveMonitorService;
	@Autowired
	private DuneMoveReportService duneMoveReportService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private OperatingLogService operatingLogService;
	private StringBuffer comments;
	
	private List<TbWeatherStation> stationList;
	@RequestMapping("/dune")
	public ModelAndView execute(TbDuneMoveMonitor duneMove, HttpServletRequest request){
		//定义并初始化(页码，页显数)
		
		List<TbDuneMoveMonitor> duneMoves = new ArrayList<TbDuneMoveMonitor>();
		duneMoves = duneMoveMonitorService.findPageByRowBounds(SecurityUtils.getCurrentUserIdMap(), duneMove.getBounds());
		//获取记录总数
		duneMove.setTotalCount(duneMoveMonitorService.getTotalCount());
		request.setAttribute("page", duneMove);
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/dunemove/dune", "duneMoves", duneMoves);
		return modelAndView;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(TbDuneMoveMonitor duneMove , HttpServletRequest request){
		//用户具有的权限站点
		stationList = commonService.getStationListByUserId(SecurityUtils.getCurrentUserIdMap());
		request.setAttribute("stationList", stationList);
		request.setAttribute("windDirections", new SelectOptions().getWindDirections());
		request.setAttribute("longtitudes", duneMoveMonitorService.getLongtitudes(SecurityUtils.getCurrentUserIdMap()));
		request.setAttribute("latitudes", duneMoveMonitorService.getLatitudes(SecurityUtils.getCurrentUserIdMap()));
		
		//判断是否传入用户ID,是：编辑，否：新增
		if(duneMove.getId() != null && !("".equals(duneMove.getId()))){
			duneMove = duneMoveMonitorService.findById(duneMove.getId());
		}else{
			duneMove.setOperationTime(new Date());
		}
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/dunemove/duneEdit", "duneMove", duneMove);
		return modelAndView;
	}
	
	@RequestMapping("/save")
	public void saveAndUpdate(TbDuneMoveMonitor duneMove, HttpServletRequest request, HttpServletResponse response){
		String result = "0";
		duneMove.setDuneMoveMonitorDate(duneMove.getOperationTime());

		//判断是否传入用户ID,是：编辑，否：新增
		if(duneMove.getId() != null && !("".equals(duneMove.getId()))){
			duneMove.setUserId(SecurityUtils.getCurrentUserId());
			duneMoveMonitorService.update(duneMove);
			//保存操作日志
			comments = new StringBuffer();
			comments.append("更新一条沙丘移动监测数据（站点");
			comments.append(duneMove.getStationId());
			comments.append("，录入时间：");
			comments.append(DateUtils.formatDate(duneMove.getOperationTime(), DateUtils.YYYY_MM_DD_HH_MM_SS));
			comments.append(")");
			operatingLogService.save(Constants.MODULE_DUNE_MOVE, comments.toString());
			
			result = "1";
		}else{
			duneMove.setUserId(SecurityUtils.getCurrentUserId());
			duneMoveMonitorService.insert(duneMove);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("新增一条沙丘移动监测数据（站点");
			comments.append(duneMove.getStationId());
			comments.append("，录入时间：");
			comments.append(DateUtils.formatDate(duneMove.getOperationTime(), DateUtils.YYYY_MM_DD_HH_MM_SS));
			comments.append(")");
			operatingLogService.save(Constants.MODULE_DUNE_MOVE, comments.toString());
			
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
	public Object delete(Integer duneMoveId, HttpServletRequest request){
		boolean result = false;
		if(duneMoveId != null && !("".equals(duneMoveId))){
			duneMoveMonitorService.delete(duneMoveId);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("删除一条沙丘移动监测数据");
			operatingLogService.save(Constants.MODULE_DUNE_MOVE, comments.toString());
			
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
		
		return new ModelAndView("/dunemove/reportHeader", "stationList", stationList);
	}
	
	@RequestMapping("/createReport")
	public void reportHeader(ReportHeader reportHeader, HttpServletResponse response){
		//设置当前用户ID
		reportHeader.setCurrentUserId(SecurityUtils.getCurrentUserId());
//		//获取报表数据
//		TbDuneMoveMonitor duneMoveMonitor = duneMoveMonitorService.getDuneMoveReportData(reportHeader);
//		
//		if(duneMoveMonitor != null){
//			//删除相同月份的报表数据
//			duneMoveReportService.deleteByTime(duneMoveMonitor);
//			//记录报表数据
//			duneMoveReportService.insert(duneMoveMonitor);
//		}
//		List<TbDuneMoveMonitor> duneMoveMonitors = duneMoveReportService.findReportData(reportHeader);
		
		List<TbDuneMoveMonitor> duneMoveMonitors = duneMoveMonitorService.getRealDuneMoveReportData(reportHeader);
		
		//保存操作日志
		comments = new StringBuffer();
		comments.append("生成沙丘移动监测报表数据（站点");
		comments.append(reportHeader.getReportStationId());
		comments.append("，填表人：");
		comments.append(reportHeader.getReporterName());
		comments.append(")");
		operatingLogService.save(Constants.MODULE_DUNE_MOVE, comments.toString());
				
		//生成报表
		duneMoveMonitorService.createReport(response, reportHeader, duneMoveMonitors);
	}
	
	/**
	 * 历史土壤监测数据列表
	 * @param soilReport
	 * @return
	 */
	@RequestMapping("/duneMoveReport")
	public ModelAndView duneMoveReportPage(TbDuneMoveMonitor duneMoveMonitor, HttpServletRequest request){
		//定义并初始化(页码，页显数)
		
		List<TbDuneMoveMonitor> duneMoveReports = new ArrayList<TbDuneMoveMonitor>();
		duneMoveReports = duneMoveReportService.findPageByRowBounds(SecurityUtils.getCurrentUserIdMap(), duneMoveMonitor.getBounds());
		//获取记录总数
		duneMoveMonitor.setTotalCount(duneMoveReportService.getTotalCount());
		request.setAttribute("page", duneMoveMonitor);
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/dunemove/duneMoveReport", "duneMoveReports", duneMoveReports);
		return modelAndView;
	}
	
	@RequestMapping("/details")
	public ModelAndView details(TbDuneMoveMonitor duneMove , HttpServletRequest request){
		//判断是否传入用户ID,是：编辑，否：新增
		if(duneMove.getId() != null && !("".equals(duneMove.getId()))){
			duneMove = duneMoveReportService.findById(duneMove.getId());
		}
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/dunemove/duneReportDetails", "duneMove", duneMove);
		return modelAndView;
	}
	
	@RequestMapping("/deleteReport")
	@ResponseBody
	public Object deleteReport(Integer duneMoveReportId, HttpServletRequest request){
		boolean result = false;
		if(duneMoveReportId != null && !("".equals(duneMoveReportId))){
			duneMoveReportService.delete(duneMoveReportId);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("删除一条已生成的沙丘移动历史报表数据");
			operatingLogService.save(Constants.MODULE_DUNE_MOVE, comments.toString());
			
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
