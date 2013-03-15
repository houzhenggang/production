/**
 * 植物物种多样性监测数据报表Action
 */
package com.rubik.eds.web.plant;

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
import com.rubik.eds.entity.TbPlantDetails;
import com.rubik.eds.entity.TbPlantSpeciesMonitor;
import com.rubik.eds.entity.TbWeatherStation;
import com.rubik.eds.service.CommonService;
import com.rubik.eds.service.plant.PlantDetailsService;
import com.rubik.eds.service.plant.PlantSpeciesMonitorService;
import com.rubik.support.common.utils.DateUtils;
import com.rubik.support.security.SecurityUtils;
import com.rubik.support.service.OperatingLogService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/security/plant")
public class PlantSpeciesMonitorAction{
	
	/**
	 * 站点管理Service
	 */
	@Autowired
	private PlantSpeciesMonitorService plantSpeciesMonitorService;
	@Autowired
	private PlantDetailsService plantDetailsService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private OperatingLogService operatingLogService;
	private StringBuffer comments;
	
	private List<TbWeatherStation> stationList;
	@RequestMapping("/plant")
	public ModelAndView execute(TbPlantSpeciesMonitor plant, HttpServletRequest request){
		//定义并初始化(页码，页显数)
		List<TbPlantSpeciesMonitor> plants = new ArrayList<TbPlantSpeciesMonitor>();
		plants = plantSpeciesMonitorService.findPageByRowBounds(SecurityUtils.getCurrentUserIdMap(), plant.getBounds());
		//获取记录总数
		plant.setTotalCount(plantSpeciesMonitorService.getTotalCount());
		request.setAttribute("page", plant);
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/plant/plant", "plants", plants);
		return modelAndView;
	}
	
	@RequestMapping("/add")
	public ModelAndView edit(TbPlantSpeciesMonitor speciesMonitor, HttpServletRequest request){
		//用户具有的权限站点
		stationList = commonService.getStationListByUserId(SecurityUtils.getCurrentUserIdMap());
		request.setAttribute("stationList", stationList);
		
		//判断是否传入用户ID,是：编辑，否：新增
		if(speciesMonitor.getId() != null && !("".equals(speciesMonitor.getId()))){
			speciesMonitor = plantSpeciesMonitorService.findById(speciesMonitor.getId());
		}else{
			speciesMonitor.setOperationTime(new Date());
		}
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/plant/plantAdd", "speciesMonitor", speciesMonitor);
		return modelAndView;
	}
	
	@RequestMapping("/save")
	public void saveAndUpdate(TbPlantSpeciesMonitor plant, HttpServletRequest request, HttpServletResponse response){
		String result = "0";
		plant.setPlantMonitorDate(plant.getOperationTime());
		//新增
		//判断是否传入用户ID,是：编辑，否：新增
		if(plant.getId() != null && !("".equals(plant.getId()))){
			plant.setUserId(SecurityUtils.getCurrentUserId());
			plantSpeciesMonitorService.update(plant);
			//保存操作日志
			comments = new StringBuffer();
			comments.append("更新一条植物多样性监测数据（站点");
			comments.append(plant.getStationId());
			comments.append("，录入时间：");
			comments.append(DateUtils.formatDate(plant.getOperationTime(), DateUtils.YYYY_MM_DD_HH_MM_SS));
			comments.append(")");
			operatingLogService.save(Constants.MODULE_PLANT, comments.toString());
			
			result = "1";
		}else{
			plant.setUserId(SecurityUtils.getCurrentUserId());
			plantSpeciesMonitorService.insert(plant);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("新增一条植物多样性监测数据（站点");
			comments.append(plant.getStationId());
			comments.append("，录入时间：");
			comments.append(DateUtils.formatDate(plant.getOperationTime(), DateUtils.YYYY_MM_DD_HH_MM_SS));
			comments.append(")");
			operatingLogService.save(Constants.MODULE_PLANT, comments.toString());
			
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
	public Object delete(Integer plantId, HttpServletRequest request){
		boolean result = false;
		if(plantId != null && !("".equals(plantId))){
			plantSpeciesMonitorService.delete(plantId);
			TbPlantDetails tmpPlantDetails = new TbPlantDetails();
			tmpPlantDetails.setPlantSpeciesId(plantId);
			plantDetailsService.deleteByWhere(tmpPlantDetails);
			
			//保存操作日志
			comments = new StringBuffer();
			comments.append("删除一条植物多样性监测数据");
			operatingLogService.save(Constants.MODULE_PLANT, comments.toString());
			
			result = true;
		}
		return result;
	}
	
	/**
	 * 添加植物物种
	 * @param plantDetails
	 * @param request
	 * @return
	 */
	@RequestMapping("/plantDetails")
	public ModelAndView plantDetails(TbPlantDetails plantDetails, HttpServletRequest request){
		//查询
		List<String> plantNames = plantDetailsService.findByWhere(plantDetails);
		plantDetails.setPlantNames(plantNames);
		
		request.setAttribute("plantSelects", plantDetailsService.findAllPlantNames());
		
		return new ModelAndView("/plant/details", "plantDetails", plantDetails);
	}
	
	@RequestMapping("/saveDetails")
	public void saveDetails(TbPlantDetails plantDetails,HttpServletResponse response){
		String result = "0";
		//新增
		plantDetailsService.deleteByWhere(plantDetails);
		//重新插入记录
		plantDetailsService.insert(plantDetails);
		result = "1";
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/checkExsit")
	public void checkRecordExsit(TbPlantSpeciesMonitor plantSpeciesMonitor, HttpServletResponse response){
		try {
			if(plantSpeciesMonitorService.findByDate(plantSpeciesMonitor) == 0){
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
		stationList = commonService.getStationListByUserId(SecurityUtils.getCurrentUserIdMap());
		
		List<ReportHeader> reporters = commonService.findAll();
		request.setAttribute("reporters", reporters);
		
		reportHeader.setReportStartDate(new Date());
		reportHeader.setReportDate(new Date());
		request.setAttribute("reportHeader", "reportHeader");
		
		return new ModelAndView("/plant/reportHeader", "stationList", stationList);
	}
	
	@RequestMapping("/createReport")
	public void reportHeader(ReportHeader reportHeader, HttpServletResponse response){
		//设置当前用ID
		reportHeader.setCurrentUserId(SecurityUtils.getCurrentUserId());
		//获取监测数据
		List<TbPlantSpeciesMonitor> speciesMonitors = plantSpeciesMonitorService.getPlantSpeciesReportData(reportHeader);
		//设置监测时间
		if(speciesMonitors != null && speciesMonitors.size() > 0){
			reportHeader.setReportEndDate(speciesMonitors.get(0).getOperationTime());
		}
		
		//保存操作日志
		comments = new StringBuffer();
		comments.append("生成植物多样性监测报表数据（站点");
		comments.append(reportHeader.getReportStationId());
		comments.append("，填表人：");
		comments.append(reportHeader.getReporterName());
		comments.append(")");
		operatingLogService.save(Constants.MODULE_PLANT, comments.toString());
		
		//生成报表
		if("inner".equals(reportHeader.getReportMonitorArea())){
			//围栏内
			plantSpeciesMonitorService.createReportInner(response, reportHeader, speciesMonitors);
		}else{
			//围栏外
			plantSpeciesMonitorService.createReportOuter(response, reportHeader, speciesMonitors);
		}
	}
	
	@InitBinder 
	public void initBinder(WebDataBinder binder) { 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		dateFormat.setLenient(true); 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); 
	} 
}
