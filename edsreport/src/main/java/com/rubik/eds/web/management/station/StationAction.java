package com.rubik.eds.web.management.station;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rubik.eds.common.constants.Constants;
import com.rubik.eds.entity.TbWeatherStation;
import com.rubik.eds.service.management.station.StationManagementService;
import com.rubik.support.service.OperatingLogService;

@Controller
@RequestMapping("/security/management")
public class StationAction {

	/**
	 * 站点管理Service
	 */
	@Autowired
	private StationManagementService stationManagementService;
	@Autowired
	private OperatingLogService operatingLogService;
	private StringBuffer comments;
	
	@RequestMapping("/station/station")
	public ModelAndView execute(TbWeatherStation station, HttpServletRequest request){
		//定义并初始化(页码，页显数)
		
		List<TbWeatherStation> stations = new ArrayList<TbWeatherStation>();
		stations = stationManagementService.findPageByRowBounds(station.getBounds());
		//获取记录总数
		station.setTotalCount(stationManagementService.getTotalCount());
		request.setAttribute("page", station);
		
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/management/station/station", "stationList", stations);
		return modelAndView;
	}
	
	@RequestMapping("/station/edit")
	public ModelAndView edit(TbWeatherStation station , HttpServletRequest request){
		
		//判断是否传入用户ID,是：编辑，否：新增
		if(station.getStationId() != null && !("".equals(station.getStationId()))){
			station = stationManagementService.findById(station.getStationId());
		}
		
		//封装返回数据
		ModelAndView modelAndView = new ModelAndView("/management/station/stationEdit", "station", station);
		return modelAndView;
	}
	
	@RequestMapping("/station/save")
	public void saveAndUpdate(TbWeatherStation station,String isAdd, HttpServletRequest request, HttpServletResponse response){
		String result = "0";
		
		//判断是否传入用户ID,是：编辑，否：新增
		if(isAdd != null && !("".equals(isAdd))){
			stationManagementService.update(station);
			//保存操作日志
			comments = new StringBuffer();
			comments.append("更新一条站点信息（站点");
			comments.append(station.getStationId());
			comments.append("，站名：");
			comments.append(station.getStationName());
			comments.append(")");
			operatingLogService.save(Constants.MODULE_MANAGEMENT_STATION, comments.toString());
			result = "1";
		}else{
			stationManagementService.insert(station);
			//保存操作日志
			comments = new StringBuffer();
			comments.append("新增一条站点信息（站点");
			comments.append(station.getStationId());
			comments.append("，站名：");
			comments.append(station.getStationName());
			comments.append(")");
			operatingLogService.save(Constants.MODULE_MANAGEMENT_STATION, comments.toString());
			result = "1";
		}
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/station/delete")
	@ResponseBody
	public Object delete(String stationId, HttpServletRequest request){
		boolean result = false;
		if(stationId != null && !("".equals(stationId))){
			stationManagementService.delete(stationId);
			//保存操作日志
			comments = new StringBuffer();
			comments.append("删除一条站点信息（站点：");
			comments.append(stationId);
			comments.append("）");
			operatingLogService.save(Constants.MODULE_MANAGEMENT_STATION, comments.toString());
			result = true;
		}
		return result;
	}
	
	@RequestMapping("/station/checkStationExist")
	public void checkStationExist(String stationId, HttpServletResponse response){
		String 	result = "0";
		//根据站点ID查询数据库记录
		if(stationManagementService.findById(stationId) == null){
			result = "1";
		}
		//返回验证信息
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
