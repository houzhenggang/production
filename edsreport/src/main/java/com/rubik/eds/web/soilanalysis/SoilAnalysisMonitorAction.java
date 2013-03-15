package com.rubik.eds.web.soilanalysis;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.rubik.eds.entity.SoilMonitorConstant;
import com.rubik.eds.entity.TbSoilAnalysisMonitor;
import com.rubik.eds.entity.TbWeatherStation;
import com.rubik.eds.service.CommonService;
import com.rubik.eds.service.soilanalysis.SoilAnalysisService;
import com.rubik.eds.service.soilanalysis.SoilConstantService;
import com.rubik.support.common.utils.DateUtils;
import com.rubik.support.security.SecurityUtils;
import com.rubik.support.service.OperatingLogService;

@Controller
@RequestMapping("/security/soilanalysis")
public class SoilAnalysisMonitorAction {

	/**
	 * 站点管理Service
	 */
	@Autowired
	private SoilAnalysisService soilAnalysisService;
	@Autowired
	private SoilConstantService soilConstantService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private OperatingLogService operatingLogService;
	private StringBuffer comments;
	
	private List<TbWeatherStation> stationList;
	private List<SoilMonitorConstant> soilMonitorConstants;
	
	@RequestMapping("/analysis")
	public ModelAndView execute(TbSoilAnalysisMonitor soilAnalysis, HttpServletRequest request){
		//定义并初始化(页码，页显数)
		List<TbSoilAnalysisMonitor> soilAnalysiss = new ArrayList<TbSoilAnalysisMonitor>();
		soilAnalysiss = soilAnalysisService.getSoilAnalysisByRowBounds(SecurityUtils.getCurrentUserIdMap(), soilAnalysis.getBounds());
		//获取记录总数
		soilAnalysis.setTotalCount(soilAnalysisService.getTotalCount());
		request.setAttribute("page", soilAnalysis);
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/soilanalysis/soilanalysis", "soilAnalysiss", soilAnalysiss);
		return modelAndView;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(TbSoilAnalysisMonitor soilAnalysis , HttpServletRequest request){
		//用户具有的权限站点
		soilMonitorConstants = soilConstantService.getSoilConstantListByUserId(SecurityUtils.getCurrentUserIdMap());
		request.setAttribute("stationList", soilMonitorConstants);
		
		
		//判断是否传入用户ID,是：编辑，否：新增
		if(soilAnalysis.getId() != null && !("".equals(soilAnalysis.getId()))){
			soilAnalysis = soilAnalysisService.findById(soilAnalysis.getId());
		}else{
			soilAnalysis.setOperationTime(new Date());
		}
		
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/soilanalysis/soilanalysisEdit", "soilAnalysis", soilAnalysis);
		return modelAndView;
	}
	
	@RequestMapping("/save")
	public void saveAndUpdate(TbSoilAnalysisMonitor soilAnalysis, HttpServletRequest request, HttpServletResponse response){
		String result = "0";
		soilAnalysis.setSoilAnalysisDate(soilAnalysis.getOperationTime());
		//判断是否传入用户ID,是：编辑，否：新增
		if(soilAnalysis.getId() != null && !("".equals(soilAnalysis.getId()))){
			soilAnalysis.setUserId(SecurityUtils.getCurrentUserId());
			soilAnalysisService.update(soilAnalysis);
			//保存操作日志
			comments = new StringBuffer();
			comments.append("更新一条土壤水分分析监测数据（站点");
			comments.append(soilAnalysis.getStationId());
			comments.append("，录入时间：");
			comments.append(DateUtils.formatDate(soilAnalysis.getOperationTime(), DateUtils.YYYY_MM_DD_HH_MM_SS));
			comments.append(")");
			operatingLogService.save(Constants.MODULE_SOIL_ANALYSIS, comments.toString());
			
			result = "1";
		}else{
			soilAnalysis.setUserId(SecurityUtils.getCurrentUserId());
			soilAnalysisService.insert(soilAnalysis);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("新增一条土壤水分分析监测数据（站点");
			comments.append(soilAnalysis.getStationId());
			comments.append("，录入时间：");
			comments.append(DateUtils.formatDate(soilAnalysis.getOperationTime(), DateUtils.YYYY_MM_DD_HH_MM_SS));
			comments.append(")");
			operatingLogService.save(Constants.MODULE_SOIL_ANALYSIS, comments.toString());
			
			result = "1";
		}
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Integer soilAnalysisId, HttpServletRequest request){
		boolean result = false;
		if(soilAnalysisId != null && !("".equals(soilAnalysisId))){
			soilAnalysisService.delete(soilAnalysisId);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("删除一条土壤水分分析监测数据");
			operatingLogService.save(Constants.MODULE_SOIL_ANALYSIS, comments.toString());
			
			result = true;
		}
		return result;
	}
	
	/**
	 * 土壤水分监测常量
	 * @param soilConstant
	 * @param request
	 * @return
	 */
	@RequestMapping("/constant")
	public ModelAndView constantPage(SoilMonitorConstant soilConstant, HttpServletRequest request){
		//定义并初始化(页码，页显数)
		List<SoilMonitorConstant> soilConstants = new ArrayList<SoilMonitorConstant>();
		soilConstants = soilConstantService.getSoilConstantByRowBounds(SecurityUtils.getCurrentUserIdMap(), soilConstant.getBounds());
		//获取记录总数
		soilConstant.setTotalCount(soilConstantService.getTotalCount());
		request.setAttribute("page", soilConstant);
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/soilanalysis/constant", "soilConstants", soilConstants);
		return modelAndView;
	}
	
	@RequestMapping("/constantEdit")
	public ModelAndView constantEdit(SoilMonitorConstant soilConstant , HttpServletRequest request){
		//用户具有的权限站点
		stationList = commonService.getStationListByUserId(SecurityUtils.getCurrentUserIdMap());
		request.setAttribute("stationList", stationList);
		
		
		//判断是否传入用户ID,是：编辑，否：新增
		if(soilConstant.getStationId() != null && !("".equals(soilConstant.getStationId()))){
			soilConstant = soilConstantService.findById(soilConstant.getStationId());
		}
		
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/soilanalysis/constantEdit", "soilConstant", soilConstant);
		return modelAndView;
	}
	
	@RequestMapping("/constantSave")
	public void saveAndUpdate(SoilMonitorConstant soilConstant,String changeFlag, HttpServletRequest request, HttpServletResponse response){
		String result = "0";
		//判断是否传入用户ID,是：编辑，否：新增
		if(changeFlag != null && !("".equals(changeFlag))){
			soilConstantService.update(soilConstant);
			//保存操作日志
			comments = new StringBuffer();
			comments.append("新增一条土壤水分分析—站点常量数据（站点");
			comments.append(soilConstant.getStationId());
			comments.append(")");
			operatingLogService.save(Constants.MODULE_SOIL_ANALYSIS, comments.toString());
			result = "1";
		}else{
			soilConstantService.insert(soilConstant);
			//保存操作日志
			comments = new StringBuffer();
			comments.append("更新一条土壤水分分析—站点常量数据（站点");
			comments.append(soilConstant.getStationId());
			comments.append(")");
			operatingLogService.save(Constants.MODULE_SOIL_ANALYSIS, comments.toString());
			result = "1";
		}
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/constantDelete")
	@ResponseBody
	public Object constantDelete(String soilConstantId, HttpServletRequest request){
		boolean result = false;
		if(soilConstantId != null && !("".equals(soilConstantId))){
			soilConstantService.delete(soilConstantId);
			result = true;
		}
		return result;
	}
	
	@RequestMapping("/checkExsit")
	public void checkRecordExsit(String stationId, HttpServletResponse response){
		try {
			if(soilConstantService.findById(stationId) == null){
				response.getWriter().write("1");
			}else{
				//存在当前月份数据
				response.getWriter().write("0");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/checkReportExsit")
	public void checkReportRecordExsit(ReportHeader reportHeader, HttpServletResponse response){
		try {
			//设置当前用ID
			reportHeader.setCurrentUserId(SecurityUtils.getCurrentUserId());
			if(soilAnalysisService.getReportDataByDate(reportHeader) == null){
				response.getWriter().write("1");
			}else{
				//存在当前月份数据
				response.getWriter().write("0");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/reportHeader")
	public ModelAndView reportHeader(ReportHeader reportHeader, HttpServletRequest request){
		//用户具有的权限站点
		soilMonitorConstants = soilConstantService.getSoilConstantListByUserId(SecurityUtils.getCurrentUserIdMap());
		
		reportHeader.setReportStartDate(new Date());
		reportHeader.setReportDate(new Date());
		request.setAttribute("reportHeader", "reportHeader");
		
		return new ModelAndView("/soilanalysis/reportHeader", "stationList", soilMonitorConstants);
	}
	
	@RequestMapping("/createReport")
	public void reportHeader(ReportHeader reportHeader, HttpServletResponse response){
		//设置当前用ID
		reportHeader.setCurrentUserId(SecurityUtils.getCurrentUserId());
		//获取土壤监测数据
		TbSoilAnalysisMonitor soilAnalysisMonitor =soilAnalysisService.getReportDataByDate(reportHeader);
		//获取土壤水文常量
		SoilMonitorConstant soilMonitorConstant = soilConstantService.findById(reportHeader.getReportStationId());
		
		//保存操作日志
		comments = new StringBuffer();
		comments.append("生成土壤水分分析监测报表数据（站点");
		comments.append(reportHeader.getReportStationId());
		comments.append("，填表人：");
		comments.append(reportHeader.getReporterName());
		comments.append(")");
		operatingLogService.save(Constants.MODULE_SOIL_ANALYSIS, comments.toString());
		
		//调用报文生成函数
		soilAnalysisService.createReport(response, soilAnalysisMonitor, soilMonitorConstant, reportHeader);
	}
	
	/**
	 * 检测FTP是否可连通
	 * @param response
	 * @return
	 */
	@RequestMapping("/pingFPTConnect")
	@ResponseBody
	public Map<String, Object> pingFTP(HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			soilAnalysisService.pingFtpConnect();
			map.put("isConnect", true);
		} catch (Exception e) {
			map.put("isConnect", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) { 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		dateFormat.setLenient(true); 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
	} 
}
