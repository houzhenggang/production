package com.rubik.eds.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubik.eds.dao.TbReportHeaderDao;
import com.rubik.eds.dao.TbWeatherStationDao;
import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbWeatherStation;

@Service
@Transactional
public class CommonService {

	@Autowired
	private TbWeatherStationDao weatherStationDao;
	@Autowired
	private TbReportHeaderDao reportHeaderDao;
	/**
	 * 查询用户具有的权限的站点List
	 * @param userId
	 * @return
	 */
	public List<TbWeatherStation> getStationListByUserId(Map<Object, Object> userIdMap){
		return weatherStationDao.getStationListByUserId(userIdMap);
	}
	
	/**
	 * 根据用户ID查询所有记录
	 * @param userId
	 * @return
	 */
	public List<ReportHeader> findAll(){
		return reportHeaderDao.findAll();
	}
	
	/**
	 * 插入一条记录
	 * @return
	 */
	public int insertReportRecord(ReportHeader reportHeader){
		return reportHeaderDao.insert(reportHeader);
	}
}
