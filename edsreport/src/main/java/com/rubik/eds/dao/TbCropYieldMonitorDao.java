package com.rubik.eds.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.rubik.eds.entity.ReportHeader;
import com.rubik.eds.entity.TbCropYieldMonitor;
import com.rubik.support.dao.CommonDao;

@Repository
public class TbCropYieldMonitorDao extends CommonDao<TbCropYieldMonitor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5478128089897499080L;

	@Override
	protected String getNameSpace() {
		return "cropYield";
	}

	/**
	 * 分页查询农作物生长监测数据列表
	 * @param rowBounds
	 * @return
	 */
	public List<TbCropYieldMonitor> getCropYieldByRowBounds(Map<Object, Object> userIdMap, RowBounds rowBounds){
		return getSqlSession().selectList(getNameSpace()+".findAll", userIdMap, rowBounds);
	}
	
	/**
	 * 根据报表头查询报表数据
	 * @return
	 */
	public List<TbCropYieldMonitor> getCropYieldReportData(ReportHeader reportHeader){
		return getSqlSession().selectList(getNameSpace()+".findCropYieldReportData", reportHeader);
	}
	
	/**
	 * 查询所有农作物名称
	 * @return
	 */
	public List<String> getCropNames(Map<Object, Object> userIdMap){
		return getSqlSession().selectList(getNameSpace()+".findCropNames", userIdMap);
	}
}
