package com.rubik.eds.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbGrassMonitor;
import com.rubik.support.dao.CommonDao;
import com.rubik.support.security.SecurityUtils;

@Repository
public class TbGrassMonitorDao extends CommonDao<TbGrassMonitor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 524974051059958668L;

	@Override
	protected String getNameSpace() {
		// TODO Auto-generated method stub
		return "grass";
	}

	/**
	 * 分页查询牧草监测数据列表
	 * @param rowBounds
	 * @return
	 */
	public List<TbGrassMonitor> getGrassByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return getSqlSession().selectList(getNameSpace()+".findAll", userIdMap, rowBounds);
	}
	
	/**
	 * 根据报表头查询报表数据
	 * @return
	 */
	public List<TbGrassMonitor> getGrassReportData(ReportHeader reportHeader){
		return getSqlSession().selectList(getNameSpace()+".findGrassReportData", reportHeader);
	}
	
	/**
	 * 查询最新的一条天然草场记录
	 * @param stationId
	 * @return
	 */
	public TbGrassMonitor findLastedRecord(String stationId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", SecurityUtils.getCurrentUserIdMap().get("userId"));
		paramMap.put("stationId", stationId);
		return getSqlSession().selectOne(getNameSpace()+".findLastedRecord", paramMap);
	}
}
